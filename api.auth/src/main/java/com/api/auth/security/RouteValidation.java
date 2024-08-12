package com.api.auth.security;

import com.api.auth.dto.RequestDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-path")
public class RouteValidation {

    private List<RequestDTO> path;

    public boolean isAdmin(RequestDTO requestDTO) {
        return path
                .stream()
                .anyMatch(
                        p -> Pattern
                                .matches(
                                        p.getUri(),requestDTO.getUri()
                                )
                                && p.getMethod().equals(requestDTO.getMethod())
                );
    }
}
