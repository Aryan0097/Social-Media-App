package com.app.socialmediaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.socialmediaapp.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
