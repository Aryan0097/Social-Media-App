package com.app.socialmediaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.socialmediaapp.entities.User;
import com.app.socialmediaapp.responses.FollowerOrFollowingResponse;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    
    @Query("SELECT new com.app.socialmediaapp.responses.FollowerOrFollowingResponse(u.id, u.username) " + "FROM User u JOIN u.following f WHERE f.id = :userId")
    List<FollowerOrFollowingResponse> findFollowingByUserId(@Param("userId") Long userId);

    
    @Query("SELECT new com.app.socialmediaapp.responses.FollowerOrFollowingResponse(u.id, u.username) " + "FROM User u JOIN u.followers f WHERE f.id = :userId")
    	List<FollowerOrFollowingResponse> findFollowersByUserId(@Param("userId") Long userId);

}
