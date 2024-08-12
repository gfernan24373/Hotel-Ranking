package com.ms.user.ms_user.producer;

import com.ms.user.ms_user.dto.jms.JmsEmailDTO;

public interface IMsEmailProducer {

    void generateTransactionEmail(JmsEmailDTO jmsEmailDTO);
}
