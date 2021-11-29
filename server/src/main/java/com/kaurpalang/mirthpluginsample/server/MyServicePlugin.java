package com.kaurpalang.mirthpluginsample.server;

import com.kaurpalang.mirth.annotationsplugin.annotation.ServerClass;
import com.kaurpalang.mirthpluginsample.shared.MyConstants;
import com.kaurpalang.mirthpluginsample.shared.MyPermissions;
import com.kaurpalang.mirthpluginsample.shared.interfaces.MyServletInterface;
import com.mirth.connect.client.core.api.util.OperationUtil;
import com.mirth.connect.model.ExtensionPermission;
import com.mirth.connect.plugins.ServicePlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ServerClass
public class MyServicePlugin implements ServicePlugin {

    @Override
    public void init(Properties properties) {
        System.out.println("Hello world from init!");
    }

    @Override
    public void update(Properties properties) {
        // We don't need to do anything here.
    }

    @Override
    public Properties getDefaultProperties() {
        return new Properties();
    }

    @Override
    public ExtensionPermission[] getExtensionPermissions() {
        ExtensionPermission getPermission = new ExtensionPermission (
                MyConstants.PLUGIN_POINTNAME,
                MyPermissions.GETSTH,
                "Allows getting important information from our plugin",
                OperationUtil.getOperationNamesForPermission(MyPermissions.GETSTH, MyServletInterface.class), new String[] {}
        );

        return new ExtensionPermission[] {
                getPermission
        };
    }

    @Override
    public Map<String, Object> getObjectsForSwaggerExamples() {
        return new HashMap<>();
    }

    @Override
    public String getPluginPointName() {
        return MyConstants.PLUGIN_POINTNAME;
    }

    @Override
    public void start() {
        System.out.println("Hello world from start!");
    }

    @Override
    public void stop() {
        System.out.println("Good bye world!");
    }
}
