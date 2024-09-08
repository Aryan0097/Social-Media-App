package com.app.socialmediaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.socialmediaapp.entities.User;

import com.app.socialmediaapp.responses.UserResponse;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    
    @Query("SELECT new com.app.socialmediaapp.responses.UserResponse(u.id, u.username) " + "FROM User u JOIN u.following f WHERE f.id = :userId")
    List<UserResponse> findFollowingByUserId(@Param("userId") Long userId);

    
    @Query("SELECT new com.app.socialmediaapp.responses.UserResponse(u.id, u.username) " + "FROM User u JOIN u.followers f WHERE f.id = :userId")
    	List<UserResponse> findFollowersByUserId(@Param("userId") Long userId);
    
    @Query("SELECT new com.app.socialmediaapp.responses.UserResponse(u.id, u.username) " +
            "FROM User u WHERE u.username LIKE %:searchString%")
     List<UserResponse> searchUsersByUsername(@Param("searchString") String searchString);

}
