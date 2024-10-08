package com.ms.user.ms_user.dto.jms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JmsEmailDTO {

    private String receiver;
    private String messageBody;
    private String subject;
}
