package com.app.socialmediaapp.responses;

import com.app.socialmediaapp.entities.User;

import lombok.Data;

@Data
public class UserResponse {

    private long id;
    private int image;
    private String username;

    public UserResponse(User user){
        this.id = user.getId();
        this.image = user.getImage();
        this.username = user.getUsername();
    }

    public UserResponse(Long id, String username, int image) {
        this.id = id;
        this.username = username;
        this.image = image;
    }
}
