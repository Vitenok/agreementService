package com.iti.facade;

import com.iti.dto.AgreementRequest;
import com.iti.dto.AgreementResponse;
import com.iti.dto.AgreementStatus;
import com.iti.service.agreement.AgreementException;
import com.iti.service.agreement.AgreementService;
import com.iti.service.agreement.AgreementStatusException;
import com.iti.service.agreement.CustomerException;
import com.iti.service.email.EmailService;
import com.iti.service.email.EmailUtils;
import com.iti.service.email.dto.EmailDto;
import com.iti.service.email.dto.EmailStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgreementFacadeImpl implements AgreementFacade {

    private static Log log = LogFactory.getLog(AgreementFacadeImpl.class);

    @Autowired
    AgreementService agreementService;

    @Autowired
    EmailService emailService;

    @Override
    public AgreementResponse createAgreement(AgreementRequest agreementRequest) {

        try {

            long customerId = 0;
            try {
                customerId = agreementService.createCustomer(agreementRequest.getCustomer());
            } catch (CustomerException e) {
                log.error("Customer can't be created", e);
                return new AgreementResponse(INVALID_ID, AgreementStatus.ERROR);
            }

            long agreementId = 0;
            try {
                agreementId = agreementService.createAgreement(customerId, agreementRequest.getInsuranceType());
            } catch (AgreementException e) {
                log.error("Agreement can't be created", e);
                return new AgreementResponse(INVALID_ID, AgreementStatus.ERROR);
            }

            EmailDto email = EmailUtils.createEmailFrom(agreementId, customerId, agreementRequest.getCustomer());
            EmailStatus emailStatus = emailService.send(email);

            if (EmailStatus.FAIL.equals(emailStatus)) {
                // TODO EmailStatus can be better described (according to response from email service)
                log.error(String.format("Email about agreement [%d] with customer [%d] was not sent", agreementId, customerId));
                return new AgreementResponse(INVALID_ID, AgreementStatus.ERROR);
            }

            try {
                agreementService.updateAgreementStatus(agreementId, AgreementStatus.SENT);
            } catch (AgreementStatusException e) {
                //Possible solutions of collision:
                //1. Try to update status few more times
                //2. Try to revert email sending (for ex. use sending delay)
                //3. Try to use different customer notification channels (if any)
                //4. Notify support to contact AgreementService team for manual status update
                //5. Notify CEO of AgreementService system
                //6. Send email to customer about ignoring previous
                //7. Notify support to contact customer
                log.error("Agreement can't be updated", e);
                return new AgreementResponse(INVALID_ID, AgreementStatus.ERROR);
            }

            return new AgreementResponse(agreementId, AgreementStatus.SENT);
        } catch (Exception e) {
            log.error("Unknown exception", e);
        }
        return new AgreementResponse(INVALID_ID, AgreementStatus.ERROR);
    }
}
