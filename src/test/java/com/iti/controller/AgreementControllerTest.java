package com.iti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iti.dto.*;
import com.iti.facade.AgreementFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgreementControllerTest {

    @MockBean
    AgreementFacade agreementFacade;

    @LocalServerPort
    private int port;

    private URI uri;

    private JacksonTester<AgreementRequest> json;

    @Before
    public void setUp() throws Exception {
        this.uri = new URI("http://localhost:" + port);

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void testCreateAgreement() throws Exception {
        AgreementResponse expected = new AgreementResponse(1234, AgreementStatus.SENT);
        doReturn(expected).when(agreementFacade).createAgreement(any(AgreementRequest.class));

        Client client = ClientBuilder.newClient();
        AgreementRequest request = new AgreementRequest();
        request.setInsuranceType(InsuranceType.CAR);
        request.setCustomer(new Customer("First Name", "Second Name", new Date()));

        Response response = client.target(uri)
                .path("api")
                .path("agreement")
                .path("create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));

        AgreementResponse actual = response.readEntity(AgreementResponse.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateAgreementInvalidInput() throws Exception {
        Client client = ClientBuilder.newClient();

        Response response = client.target(uri)
                .path("api")
                .path("agreement")
                .path("create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new AgreementRequest(), MediaType.APPLICATION_JSON));

        String errorMessage = response.readEntity(String.class);
        assertTrue(errorMessage.contains("may not be null"));
    }

}