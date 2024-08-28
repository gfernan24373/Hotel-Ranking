package com.api.auth.security;

import com.api.auth.dto.RequestDTO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-paths")
@Data
public class RouteValidation {

    private List<RequestDTO> paths;

    public boolean isAdmin(RequestDTO requestDTO) {
        return paths
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
