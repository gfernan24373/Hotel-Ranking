package com.api.auth.repository;

import com.api.auth.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthUser, Integer> {

    Optional<AuthUser> findByUsername(String username);
}
