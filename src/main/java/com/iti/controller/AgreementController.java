package com.iti.controller;

import com.iti.dto.AgreementRequest;
import com.iti.facade.AgreementFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/agreement")
@RestController
public class AgreementController {

    @Autowired
    AgreementFacade agreementFacade;

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid AgreementRequest agreementRequest) throws ValidationException {
        return Response.ok(agreementFacade.createAgreement(agreementRequest)).build();
    }
}
