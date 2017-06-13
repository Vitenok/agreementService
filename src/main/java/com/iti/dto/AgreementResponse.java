package com.iti.dto;

import org.springframework.stereotype.Component;

@Component
public class AgreementResponse {
    private long id;
    private AgreementStatus agreementStatus;

    public AgreementResponse() {
    }

    public AgreementResponse(long id, AgreementStatus agreementStatus) {
        this.id = id;
        this.agreementStatus = agreementStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AgreementStatus getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(AgreementStatus agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgreementResponse)) return false;

        AgreementResponse that = (AgreementResponse) o;

        return id == that.id && agreementStatus == that.agreementStatus;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (agreementStatus != null ? agreementStatus.hashCode() : 0);
        return result;
    }
}
