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

        return new ExtensionPermission[] {getPermission};
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
