package com.ms.user.ms_user.controller.doc;

import com.ms.user.ms_user.dto.UserDTO;
import com.ms.user.ms_user.model.UserEntity;
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

@Tag(name="User", description="API exposed for management all user")
@RequestMapping("api/v1/user")
public interface UserDoc {

    @Operation(summary="create user", description="Create a user")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "user created successfully",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )

    @PostMapping
    ResponseEntity<UserEntity> create(@Valid @RequestBody UserDTO userDTO);

    @PostMapping("/save")
    ResponseEntity<UserEntity> createForm2(@RequestBody UserEntity userEntity);


    @Operation(summary="get user by id", description="get the info of a user by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "user found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<UserEntity> getById(@PathVariable String id);

    @Operation(summary="get a list of users", description="Retrieve all users info")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "user list",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @GetMapping()
    ResponseEntity<List<UserEntity>> listUsers();

    @Operation(summary="get user by document", description="get the info of a user by document")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "user found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @GetMapping("/document/{document}")
    ResponseEntity<UserEntity> getByDocument(@PathVariable String document);

    @Operation(summary="delete user by id", description="delete user registry by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "user deleted",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<UserEntity> deleteById(@PathVariable String id);

    @Operation(summary="update user by id", description="update the info of a user by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "user updated",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<UserEntity> updateById(@PathVariable String id,@Valid @RequestBody UserDTO userDTO);

    @Operation(summary="update user by document", description="update the info of a user by document")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "user updated",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @PutMapping("/{document}")
    ResponseEntity<UserEntity> updateByDocument(@PathVariable String document,@Valid @RequestBody UserDTO userDTO);

    @Operation(summary="get ranking by user id", description="get the info of ranking by user id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "ranking found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "internal server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "endpoint not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @GetMapping("/{id}/ranking")
    ResponseEntity<?> getReviewByUserId(@PathVariable String id);

}
