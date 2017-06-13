package com.iti.service.agreement;

public class AgreementStatusException extends Exception {
    public AgreementStatusException() {
    }

    public AgreementStatusException(String message) {
        super(message);
    }

    public AgreementStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
