package com.iti.service.agreement;

import com.iti.dto.AgreementStatus;
import com.iti.dto.Customer;
import com.iti.dto.InsuranceType;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class SimpleAgreementServiceImpl implements AgreementService {
    @Override
    public long createCustomer(Customer customer) {
        throw new NotImplementedException();
    }

    @Override
    public long createAgreement(long customerId, InsuranceType insuranceType) {
        throw new NotImplementedException();
    }

    @Override
    public void updateAgreementStatus(long agreementId, AgreementStatus newAgreementStatus) {
        throw new NotImplementedException();
    }
}
