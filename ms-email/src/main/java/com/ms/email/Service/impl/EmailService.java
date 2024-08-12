package com.ms.email.Service.impl;

import com.ms.email.Service.IEmailService;
import com.ms.email.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailDTO emailRequestDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailRequestDto.getRecipient());
        mailMessage.setSubject(emailRequestDto.getSubject());
        mailMessage.setText(emailRequestDto.getMessageBody());
        javaMailSender.send(mailMessage);
    }
}
