package com.iti.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class AgreementRequest {

    @NotNull
    private Customer customer;

    @NotNull
    private InsuranceType insuranceType;

    public AgreementRequest() {
    }

    public AgreementRequest(Customer customer, InsuranceType insuranceType) {
        this.customer = customer;
        this.insuranceType = insuranceType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }
}