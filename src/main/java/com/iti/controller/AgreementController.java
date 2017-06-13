package com.iti.controller;

import com.iti.dto.AgreementRequest;
import com.iti.dto.AgreementResponse;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/agreement")
public class AgreementController {

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public AgreementResponse create() {
        return new AgreementResponse();
    }
}
