package com.kaurpalang.mirthpluginsample.server;

import com.kaurpalang.mirth.annotationsplugin.annotation.ServerClass;
import com.kaurpalang.mirthpluginsample.shared.Constants;
import com.mirth.connect.plugins.ServerPlugin;

@ServerClass
public class MyServerPlugin implements ServerPlugin {

    @Override
    public String getPluginPointName() {
        return Constants.PLUGIN_POINTNAME;
    }

    @Override
    public void start() {
        System.out.println("Hello World!");
    }

    @Override
    public void stop() {
        System.out.println("Goodbye World!");
    }
}
