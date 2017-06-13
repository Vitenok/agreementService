package com.iti.service.agreement;

import com.iti.dto.Address;
import com.iti.dto.AgreementStatus;
import com.iti.dto.Customer;
import com.iti.dto.InsuranceType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class SimpleAgreementServiceImpl implements AgreementService {
    @Override
    public long createCustomer(Customer customer, Address address) {
        throw new NotImplementedException();
    }

    @Override
    public long createAgreement(long customerId, InsuranceType insuranceType) {
        throw new NotImplementedException();
    }

    @Override
    public boolean updateAgreementStatus(long agreementId, AgreementStatus newAgreementStatus) {
        throw new NotImplementedException();
    }
}
