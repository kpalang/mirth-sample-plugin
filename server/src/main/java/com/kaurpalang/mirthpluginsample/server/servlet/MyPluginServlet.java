package com.kaurpalang.mirthpluginsample.server.servlet;

import com.kaurpalang.mirth.annotationsplugin.annotation.ApiProvider;
import com.kaurpalang.mirth.annotationsplugin.type.ApiProviderType;
import com.kaurpalang.mirthpluginsample.shared.MyConstants;
import com.kaurpalang.mirthpluginsample.shared.interfaces.MyServletInterface;
import com.kaurpalang.mirthpluginsample.shared.model.MyInfoObject;
import com.mirth.connect.server.api.MirthServlet;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@ApiProvider(type = ApiProviderType.SERVER_CLASS)
public class MyPluginServlet extends MirthServlet implements MyServletInterface {

    public MyPluginServlet(@Context HttpServletRequest request, @Context SecurityContext sc) {
        super(request, sc, MyConstants.PLUGIN_POINTNAME);
    }

    @Override
    public MyInfoObject getSomething(String identifier) {
        String data = String.format("<%s> Some important informations", identifier);
        return new MyInfoObject(data);
    }
}
