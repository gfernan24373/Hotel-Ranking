package com.ms.ranking.com.ms.ranking.controller.doc;

import com.ms.ranking.com.ms.ranking.dto.RankingDTO;
import com.ms.ranking.com.ms.ranking.model.RankingDocument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name="Ranking", description="API exposed for management all user")
@RequestMapping("api/v1/ranking")
public interface RankingDoc {

    @Operation(summary="create the ranking", description="get all the users insight")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Ranking created successfully",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @PostMapping("/create")
    ResponseEntity<RankingDocument> create(@Valid @RequestBody RankingDTO rankingDTO);

    @Operation(summary="get ranking by user id", description="get the ranking of the hotel by user id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Hotel found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @GetMapping("/{userId}")
    ResponseEntity<List<RankingDocument>> getByUserId(@PathVariable String userId);

    @Operation(summary="get ranking by hotel id", description="get the ranking of the hotel by hotel id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Hotel found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @GetMapping("/{hotelId}")
    ResponseEntity<List<RankingDocument>> getByHotelId(@PathVariable String hotelId);

}
