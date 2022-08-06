/*
 * Copyright 2021 Kaur Palang
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
        return this.mainSettingsPanel;
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
