package com.ms.user.ms_user.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // to add the getter and setter implicit
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    @NotBlank
    @NotEmpty
    @Size(min = 6)
    private String document;

    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[^0-9]*$", message = "name only contains letters")
    private String name;

    @Email
    private String email;

    @NotEmpty
    @NotBlank
    private String information;
}
