package com.ms.user.ms_user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRankingsDTO {

    private String id;

    private String document;

    private String name;

    private String email;

    private String information;

    private List<RankingDTO> rankings;
}
