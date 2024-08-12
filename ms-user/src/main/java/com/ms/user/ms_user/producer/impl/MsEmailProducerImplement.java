package com.ms.user.ms_user.producer.impl;

import com.ms.user.ms_user.dto.jms.JmsEmailDTO;
import com.ms.user.ms_user.producer.IMsEmailProducer;
import com.ms.user.ms_user.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsEmailProducerImplement implements IMsEmailProducer {

    private final JmsTemplate jmsTemplate;

    public MsEmailProducerImplement(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void generateTransactionEmail(JmsEmailDTO jmsEmailDTO) {
        try {
            final String messageBody = JsonUtil.convertJson(jmsEmailDTO);

            //true = go to public/subscriber
            //false = go to a qeue
            jmsTemplate.setPubSubDomain(false);
            jmsTemplate.convertAndSend(
                    "queue.msemail.generate_email",
                    messageBody
            );
        } catch(JmsException e) {
            log.error(e.getMessage(),e);
        }
    }
}
