package com.app.socialmediaapp.requests;

import lombok.Data;

@Data
public class CreateLikeRequest {

    private Long id;
    private Long userId;
    private Long postId;

}
