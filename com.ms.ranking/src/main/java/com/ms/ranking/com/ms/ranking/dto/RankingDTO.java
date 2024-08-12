package com.ms.ranking.com.ms.ranking.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data // to add the getter and setter implicit
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RankingDTO {

    @NotBlank
    @NotEmpty
    @Size(min = 6)
    private String userId;

    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[^0-9]*$", message = "name only contains letters")
    private String hotelId;


    private int score;

    @NotEmpty
    @NotBlank
    private String review;
}
