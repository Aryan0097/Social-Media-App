package com.app.socialmediaapp.requests;

import lombok.Data;

@Data
public class UpdateUserRequest {
	private String username;
	private String imageName;
	private String imageType;
	private byte[] imageDate;
}
