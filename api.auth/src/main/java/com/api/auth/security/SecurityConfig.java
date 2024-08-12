package com.api.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // container level settings
@EnableWebSecurity //
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // deshabilita CSRF (Cross-Site)
                .authorizeHttpRequests(
                        auth -> auth
                                .anyRequest()
                                .permitAll() // indica que se permiten todas las solicitudes
                );
        return httpSecurity.build();
    }
}
