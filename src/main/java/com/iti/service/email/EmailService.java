package com.iti.service.email;

import com.iti.service.email.dto.EmailDto;
import com.iti.service.email.dto.EmailStatus;


public interface EmailService {
    public EmailStatus send(EmailDto email);
}
