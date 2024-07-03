#!/usr/bin/env bash

echo "########################################"
echo
echo "   Building jars..."
echo
echo "########################################"
mvn install package
STATUS=$?
if [ $STATUS -ne 0 ]; then
  echo "---------- Building failed"
  exit 1
fi

PLUGIN_PATH=$(mvn exec:exec --non-recursive --quiet -Dexec.executable="echo" -Dexec.args='${mirth.plugin.path}')
ARTIFACT_ID=$(mvn exec:exec --non-recursive --quiet -Dexec.executable="echo" -Dexec.args='${project.artifactId}')

echo "########################################"
echo
echo "   Copying libraries..."
echo
echo "########################################"
rm -rf "$PLUGIN_PATH" # basically clean
mkdir -p "$PLUGIN_PATH/libs"
cp libs/runtime/{client,server,shared}/*.jar "$PLUGIN_PATH/libs/"

echo "########################################"
echo
echo "   Generating plugin.xml..."
echo
echo "########################################"
mvn -N com.kaurpalang:mirth-plugin-maven-plugin:3.0.0:generate-plugin-xml

STATUS=$?
if [ $STATUS -ne 0 ]; then
  echo "---------- Plugin.xml generation failed"
  exit 1
fi

cp plugin.xml "$PLUGIN_PATH/"
echo "########################################"
echo
echo "   Signing jars..."
echo
echo "########################################"

##################################################################
#
# Method 1
#
##################################################################
cp {client,server,shared}/target/*.jar "$PLUGIN_PATH/"
# End of method 1
##################################################################

##################################################################
#
# Method 2
#
##################################################################
mkdir "$PLUGIN_PATH/signing_input/"
cp {client,server,shared}/target/*.jar "$PLUGIN_PATH/signing_input/"
modules=("server client shared")
for module in $modules
do
  current_jar="$PLUGIN_PATH/signing_input/$ARTIFACT_ID-$module.jar"
  echo "signing $current_jar"
  jarsigner \
    -keystore certificate/keystore.jks \
    -storepass "storepass" \
    -keypass "keypass" \
    -signedjar "$PLUGIN_PATH/$ARTIFACT_ID-$module.jar" \
    "$current_jar" \
    selfsigned
done
# End of method 2
##################################################################

rm -rf "$PLUGIN_PATH/signing_input/"

echo "########################################"
echo
echo "   Packaging plugin..."
echo
echo "########################################"
zip -r $PLUGIN_PATH "$PLUGIN_PATH"
