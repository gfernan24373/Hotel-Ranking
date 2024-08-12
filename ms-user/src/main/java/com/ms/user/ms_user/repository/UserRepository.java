package com.ms.user.ms_user.repository;

import com.ms.user.ms_user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByDocument(String document); //just in case the object does not exist.
}
