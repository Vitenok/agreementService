package com.iti.service.agreement;

import com.iti.dto.AgreementStatus;
import com.iti.dto.Customer;
import com.iti.dto.InsuranceType;

public interface AgreementService {
    long createCustomer(Customer customer) throws CustomerException;

    long createAgreement(long customerId, InsuranceType insuranceType) throws AgreementException;

    void updateAgreementStatus(long agreementId, AgreementStatus newAgreementStatus) throws AgreementStatusException;

}
