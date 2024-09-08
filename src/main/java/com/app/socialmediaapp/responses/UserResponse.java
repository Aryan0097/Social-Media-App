package com.app.socialmediaapp.responses;

import com.app.socialmediaapp.entities.User;

import lombok.Data;

@Data
public class UserResponse {

    private long id;
    private String username;


    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }


	public UserResponse(long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

}
