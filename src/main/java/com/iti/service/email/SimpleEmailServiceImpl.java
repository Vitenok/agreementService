package com.iti.service.email;

import com.iti.service.email.dto.EmailDto;
import com.iti.service.email.dto.EmailStatus;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class SimpleEmailServiceImpl implements EmailService {
    @Override
    public EmailStatus send(EmailDto email) {
        throw new NotImplementedException();
    }
}
