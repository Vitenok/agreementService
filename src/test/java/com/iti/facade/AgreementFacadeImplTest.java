package com.iti.facade;

import com.iti.dto.*;
import com.iti.service.agreement.AgreementException;
import com.iti.service.agreement.AgreementService;
import com.iti.service.agreement.AgreementStatusException;
import com.iti.service.agreement.CustomerException;
import com.iti.service.email.EmailService;
import com.iti.service.email.dto.EmailDto;
import com.iti.service.email.dto.EmailStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgreementFacadeImplTest {

    @Autowired
    AgreementFacade agreementFacade;

    @MockBean
    AgreementService agreementService;

    @MockBean
    EmailService emailService;

    @Test
    public void createAgreement() throws Exception {
        long customerId = 1l;
        long agreementId = 2l;
        InsuranceType insuranceType = InsuranceType.CAR;
        EmailStatus emailStatus = EmailStatus.OK;
        AgreementRequest request = new AgreementRequest(new Customer(), insuranceType);

        AgreementResponse expected = new AgreementResponse(agreementId, AgreementStatus.SENT);

        doReturn(customerId).when(agreementService).createCustomer(any(Customer.class));
        doReturn(agreementId).when(agreementService).createAgreement(eq(customerId), eq(insuranceType));
        doReturn(emailStatus).when(emailService).send(any(EmailDto.class));
        doNothing().when(agreementService).updateAgreementStatus(eq(agreementId), eq(AgreementStatus.SENT));

        AgreementResponse actual = agreementFacade.createAgreement(request);

        assertEquals(expected, actual);

    }

    @Test
    public void cantCreateCustomer() throws CustomerException {
        doThrow(CustomerException.class).when(agreementService).createCustomer(any(Customer.class));

        AgreementRequest request = new AgreementRequest(null, null);
        AgreementResponse actual = agreementFacade.createAgreement(request);

        AgreementResponse expected = new AgreementResponse(AgreementFacade.INVALID_ID, AgreementStatus.ERROR);
        assertEquals(expected, actual);

    }

    @Test
    public void cantCreateAgreement() throws AgreementException, CustomerException {
        long customerId = 1l;
        doReturn(customerId).when(agreementService).createCustomer(any(Customer.class));

        InsuranceType insuranceType = InsuranceType.CAR;
        doThrow(AgreementException.class).when(agreementService).createAgreement(eq(customerId), eq(insuranceType));

        AgreementRequest request = new AgreementRequest(null, insuranceType);
        AgreementResponse actual = agreementFacade.createAgreement(request);

        AgreementResponse expected = new AgreementResponse(AgreementFacade.INVALID_ID, AgreementStatus.ERROR);
        assertEquals(expected, actual);

    }

    @Test
    public void cantSendEmail() throws AgreementException, CustomerException {
        long customerId = 1l;
        long agreementId = 2l;
        InsuranceType insuranceType = InsuranceType.CAR;
        EmailStatus emailStatus = EmailStatus.FAIL;

        doReturn(customerId).when(agreementService).createCustomer(any(Customer.class));
        doReturn(agreementId).when(agreementService).createAgreement(eq(customerId), eq(insuranceType));
        doReturn(emailStatus).when(emailService).send(any(EmailDto.class));

        AgreementRequest request = new AgreementRequest(null, insuranceType);
        AgreementResponse actual = agreementFacade.createAgreement(request);

        AgreementResponse expected = new AgreementResponse(AgreementFacade.INVALID_ID, AgreementStatus.ERROR);
        assertEquals(expected, actual);
    }

    @Test
    public void cantUpdateStatus() throws AgreementException, CustomerException, AgreementStatusException {
        long customerId = 1l;
        long agreementId = 2l;
        InsuranceType insuranceType = InsuranceType.CAR;
        EmailStatus emailStatus = EmailStatus.OK;
        AgreementRequest request = new AgreementRequest(new Customer(), insuranceType);

        AgreementResponse expected = new AgreementResponse(AgreementFacade.INVALID_ID, AgreementStatus.ERROR);

        doReturn(customerId).when(agreementService).createCustomer(any(Customer.class));
        doReturn(agreementId).when(agreementService).createAgreement(eq(customerId), eq(insuranceType));
        doReturn(emailStatus).when(emailService).send(any(EmailDto.class));
        doThrow(AgreementStatusException.class).when(agreementService).updateAgreementStatus(eq(agreementId), eq(AgreementStatus.SENT));

        AgreementResponse actual = agreementFacade.createAgreement(request);

        assertEquals(expected, actual);
    }

    @Test
    public void unknowErrorHappened() throws CustomerException {
        doThrow(Exception.class).when(agreementService).createCustomer(any(Customer.class));

        AgreementRequest request = new AgreementRequest(null, null);
        AgreementResponse actual = agreementFacade.createAgreement(request);

        AgreementResponse expected = new AgreementResponse(AgreementFacade.INVALID_ID, AgreementStatus.ERROR);
        assertEquals(expected, actual);

    }

}