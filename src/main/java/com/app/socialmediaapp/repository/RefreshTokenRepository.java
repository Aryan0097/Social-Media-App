package com.app.socialmediaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.socialmediaapp.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findByUserId(Long userId);
}
