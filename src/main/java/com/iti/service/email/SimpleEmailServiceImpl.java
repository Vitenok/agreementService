package com.iti.service.email;

import com.iti.service.email.dto.EmailDto;
import com.iti.service.email.dto.EmailStatus;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class SimpleEmailServiceImpl implements EmailService {
    @Override
    public EmailStatus send(EmailDto email) {
        throw new NotImplementedException();
    }
}
