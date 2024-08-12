package com.ms.email.dto;

import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmailDTO {
    @Email
    @NotBlank
    private String recipient;

    @NotBlank
    private String subject;

    @NotBlank
    private String messageBody;
}
