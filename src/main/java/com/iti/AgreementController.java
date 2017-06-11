package com.iti;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/")
public class AgreementController {

    @GET
    @Path("/agreement/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Agreement greeting(@PathParam(value = "name") String name) {
        return new Agreement(name);
    }
}
