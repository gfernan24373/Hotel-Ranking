package com.ms.ranking.com.ms.ranking.service.impl;

import com.ms.ranking.com.ms.ranking.dto.RankingDTO;
import com.ms.ranking.com.ms.ranking.model.RankingDocument;
import com.ms.ranking.com.ms.ranking.service.IRankingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.ms.ranking.com.ms.ranking.repository.RankingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RankingServiceImpl implements IRankingService {
    private final RankingRepository rankingRepository;
    @Override
    public ResponseEntity<RankingDocument> create(RankingDTO rankingDTO) {
        RankingDocument newRanking = RankingDocument.builder()
                .hotelId(rankingDTO.getHotelId())
                .userId(rankingDTO.getUserId())
                .score(rankingDTO.getScore())
                .review(rankingDTO.getReview())
                .build();
        var ranking = this.rankingRepository.save(newRanking);
        return ResponseEntity.ok(ranking);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getAll() {
        var ranking = this.rankingRepository.findAll();
        return ResponseEntity.ok(ranking);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getByUserId(String userId) {
        var findByUserId = this.rankingRepository.findByUserId(userId);
        return ResponseEntity.ok(findByUserId);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getByHotelId(String hotelId) {
        var findByHotelId = this.rankingRepository.findByHotelId(hotelId);
        return ResponseEntity.ok(findByHotelId);
    }
}
