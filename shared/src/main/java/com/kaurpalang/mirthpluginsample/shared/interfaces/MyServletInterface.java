package com.kaurpalang.mirthpluginsample.shared.interfaces;

import com.kaurpalang.mirth.annotationsplugin.annotation.ApiProvider;
import com.kaurpalang.mirth.annotationsplugin.type.ApiProviderType;
import com.kaurpalang.mirthpluginsample.shared.MyPermissions;
import com.kaurpalang.mirthpluginsample.shared.model.MyInfoObject;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.client.core.Operation;
import com.mirth.connect.client.core.api.BaseServletInterface;
import com.mirth.connect.client.core.api.MirthOperation;
import com.mirth.connect.client.core.api.Param;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@ApiProvider(type = ApiProviderType.SERVLET_INTERFACE)
@Path("/myplugin")
@Tag(name = "MyPlugin operations")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface MyServletInterface extends BaseServletInterface {

    @GET
    @Path("/getsomething")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiResponse(responseCode = "200", description = "Found the information",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = MyInfoObject.class)),
                    @Content(mediaType = MediaType.APPLICATION_XML, schema = @Schema(implementation = MyInfoObject.class))
            })
    @MirthOperation(
            name = "getSomething",
            display = "Get important information",
            permission = MyPermissions.GETSTH,
            type = Operation.ExecuteType.ASYNC
    )
    MyInfoObject getSomething(
            @Param("identifier") @Parameter(description = "The identifier of our important information to retrieve.", required = true) @QueryParam("identifier") String identifier)
            throws ClientException;
}
