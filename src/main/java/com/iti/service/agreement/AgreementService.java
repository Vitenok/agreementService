package com.iti.service.agreement;

import com.iti.dto.Address;
import com.iti.dto.AgreementStatus;
import com.iti.dto.Customer;
import com.iti.dto.InsuranceType;

interface AgreementService {
    long createCustomer(Customer customer, Address address);
    long createAgreement(long customerId, InsuranceType insuranceType);
    boolean updateAgreementStatus(long agreementId, AgreementStatus newAgreementStatus);
}
