package com.ms.ranking.com.ms.ranking.controller;

import com.ms.ranking.com.ms.ranking.controller.doc.RankingDoc;
import com.ms.ranking.com.ms.ranking.dto.RankingDTO;
import com.ms.ranking.com.ms.ranking.model.RankingDocument;
import com.ms.ranking.com.ms.ranking.service.IRankingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class RankingController implements RankingDoc {

    private final IRankingService iRankingService;

    @Override
    public ResponseEntity<RankingDocument> create(RankingDTO rankingDTO) {
        return this.iRankingService.create(rankingDTO);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getByUserId(String userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getByHotelId(String hotelId) {
        return null;
    }
}
