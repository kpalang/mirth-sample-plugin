package com.kaurpalang.mirthpluginsample.client;

import com.kaurpalang.mirth.annotationsplugin.annotation.ClientClass;
import com.kaurpalang.mirthpluginsample.client.panel.MainSettingsPanel;
import com.kaurpalang.mirthpluginsample.shared.MyConstants;
import com.mirth.connect.client.ui.AbstractSettingsPanel;
import com.mirth.connect.plugins.SettingsPanelPlugin;

@ClientClass
public class MySettingsPlugin extends SettingsPanelPlugin {

    private MainSettingsPanel mainSettingsPanel;

    public MySettingsPlugin(String name) {
        super(name);
    }

    @Override
    public AbstractSettingsPanel getSettingsPanel() {
        return null; //this.mainSettingsPanel;
    }

    @Override
    public String getPluginPointName() {
        return MyConstants.PLUGIN_POINTNAME;
    }

    @Override
    public void start() {
        System.out.println("Hello from the other slide!");
        this.mainSettingsPanel = new MainSettingsPanel();
    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }
}
