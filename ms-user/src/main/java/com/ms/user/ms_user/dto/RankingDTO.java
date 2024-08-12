package com.ms.user.ms_user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO {

    private String id;

    private String userId;

    private String hotelId;

    private int score;

    private String review;

    private HotelDTO hotel;
}
