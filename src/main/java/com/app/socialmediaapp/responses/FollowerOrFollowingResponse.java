package com.app.socialmediaapp.responses;

import lombok.Data;

@Data
public class FollowerOrFollowingResponse {
	private Long userId;
	private String username;
	
	
	public FollowerOrFollowingResponse(Long userId, String username) {
		this.userId = userId;
		this.username = username;
	}
	
	
}
