/*
 * Copyright 2021 Kaur Palang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
