package com.ms.email.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.email.Service.impl.EmailService;
import com.ms.email.model.EmailDetails;
import com.ms.email.util.JsonUtil;
import jakarta.jms.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(value = "activemq.jms-listener.enabled", matchIfMissing = true) //matchIfMissing es para la creacion del bean
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = "queue.msemail.generate_email", containerFactory = "defaultJmsListenerContainerFactory")
    private void getJmsEmail(Message<String> message, Session session) throws JmsException, JsonProcessingException {
        String jsonMessage = message.getPayload();
        log.info(jsonMessage);
        EmailDetails email = JsonUtil.convertString(jsonMessage, EmailDetails.class);
    }
}
