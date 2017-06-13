package com.iti.facade;

import com.iti.dto.AgreementRequest;
import com.iti.dto.AgreementResponse;

public interface AgreementFacade {

    long INVALID_ID = -1;

    AgreementResponse createAgreement(AgreementRequest agreementRequest);
}
