# Requires PowerShell 5+ or PowerShell Core

Write-Host "########################################"
Write-Host ""
Write-Host "   Building jars..."
Write-Host ""
Write-Host "########################################"

# Run Maven build
mvn install package
if ($LASTEXITCODE -ne 0) {
    Write-Host "---------- Building failed"
    exit 1
}

# Capture Maven properties via exec:exec
$PLUGIN_PATH = mvn help:evaluate "-Dexpression=mirth.plugin.path" -q -DforceStdout | Out-String
$PLUGIN_PATH = $PLUGIN_PATH.Trim()

$ARTIFACT_ID = mvn help:evaluate "-Dexpression=project.artifactId" -q -DforceStdout | Out-String
$ARTIFACT_ID = $ARTIFACT_ID.Trim()

Write-Host "########################################"
Write-Host ""
Write-Host "   Copying libraries..."
Write-Host ""
Write-Host "########################################"

# Remove old plugin path
if (Test-Path $PLUGIN_PATH) {
    Remove-Item -Path $PLUGIN_PATH -Recurse -Force
}
New-Item -Path "$PLUGIN_PATH\libs" -ItemType Directory -Force | Out-Null

# Copy jars
Copy-Item -Path "libs/runtime/client/*.jar" -Destination "$PLUGIN_PATH\libs\" -Force
Copy-Item -Path "libs/runtime/server/*.jar" -Destination "$PLUGIN_PATH\libs\" -Force
Copy-Item -Path "libs/runtime/shared/*.jar" -Destination "$PLUGIN_PATH\libs\" -Force

Write-Host "########################################"
Write-Host ""
Write-Host "   Generating plugin.xml..."
Write-Host ""
Write-Host "########################################"

mvn -N com.kaurpalang:mirth-plugin-maven-plugin:3.0.0:generate-plugin-xml
if ($LASTEXITCODE -ne 0) {
    Write-Host "---------- Plugin.xml generation failed"
    exit 1
}

Copy-Item -Path "plugin.xml" -Destination "$PLUGIN_PATH\" -Force

Write-Host "########################################"
Write-Host ""
Write-Host "   Signing jars..."
Write-Host ""
Write-Host "########################################"

# Method 1 (Uncomment if you want this instead)
# Copy-Item -Path "client/target/*.jar" -Destination "$PLUGIN_PATH\" -Force
# Copy-Item -Path "server/target/*.jar" -Destination "$PLUGIN_PATH\" -Force
# Copy-Item -Path "shared/target/*.jar" -Destination "$PLUGIN_PATH\" -Force

# Method 2
$signingInput = "$PLUGIN_PATH\signing_input"
New-Item -Path $signingInput -ItemType Directory -Force | Out-Null

Copy-Item -Path "client/target/*.jar" -Destination $signingInput -Force
Copy-Item -Path "server/target/*.jar" -Destination $signingInput -Force
Copy-Item -Path "shared/target/*.jar" -Destination $signingInput -Force

$modules = @("server", "client", "shared")

foreach ($module in $modules) {
    $currentJar = Join-Path $signingInput "$ARTIFACT_ID-$module.jar"
    Write-Host "signing $currentJar"
    & jarsigner `
        -keystore "certificate/keystore.jks" `
        -storepass "storepass" `
        -keypass "keypass" `
        -signedjar (Join-Path $PLUGIN_PATH "$ARTIFACT_ID-$module.jar") `
        $currentJar `
        "selfsigned"
}

# Cleanup
Remove-Item -Path $signingInput -Recurse -Force

Write-Host "########################################"
Write-Host ""
Write-Host "   Packaging plugin..."
Write-Host ""
Write-Host "########################################"

# Define subfolder name (typically same as artifact ID or plugin ID)
$ZIP_FOLDER_NAME = $PLUGIN_PATH

# Create a temporary parent folder for zipping
$ZIP_STAGE = Join-Path $PLUGIN_PATH "..\zip-stage"
$ZIP_ROOT = Join-Path $ZIP_STAGE $ZIP_FOLDER_NAME

# Clean previous
if (Test-Path $ZIP_STAGE) {
    Remove-Item -Recurse -Force $ZIP_STAGE
}

# Copy entire plugin structure into the staging subfolder
New-Item -Path $ZIP_ROOT -ItemType Directory -Force | Out-Null
Copy-Item -Path "$PLUGIN_PATH\*" -Destination $ZIP_ROOT -Recurse -Force

# Create ZIP from inside the staging area
Push-Location $ZIP_STAGE
& jar cMf "..\$ZIP_FOLDER_NAME.zip" "$ZIP_FOLDER_NAME"
Pop-Location

# Clean up temporary stage folder
Remove-Item -Recurse -Force $ZIP_STAGE
