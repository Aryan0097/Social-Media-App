package com.app.socialmediaapp.requests;

import lombok.Data;

@Data
public class CreatePostRequest {
    private long id;
    private String text;
    private String title;
    private long userId;
}
