package com.iti.service.email;

import com.iti.service.email.dto.EmailDto;
import com.iti.service.email.dto.EmailStatus;


public interface EmailService {
    EmailStatus send(EmailDto email);
}
