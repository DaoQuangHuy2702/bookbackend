package com.example.bookbackend.service.repository;

import com.example.bookbackend.service.entity.ApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, Long> {
    Optional<ApiKeyEntity> findByKey(String key);
}

