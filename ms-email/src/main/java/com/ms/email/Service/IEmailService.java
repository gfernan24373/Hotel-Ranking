package com.ms.email.Service;

import com.ms.email.dto.EmailDTO;

public interface IEmailService {
    void sendEmail(EmailDTO emailRequestDto);
}
