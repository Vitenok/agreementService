package com.iti.service.email;

import com.iti.dto.Customer;
import com.iti.service.email.dto.EmailDto;

public class EmailUtils {
    public static EmailDto createEmailFrom(long agreementId, long userId, Customer customer) {
        return new EmailDto();
    }
}
