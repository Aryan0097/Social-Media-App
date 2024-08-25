package com.app.socialmediaapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.socialmediaapp.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
