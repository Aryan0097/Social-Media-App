package com.app.socialmediaapp.responses;

import java.util.List;

import com.app.socialmediaapp.entities.Post;

import lombok.Data;

@Data
public class PostResponse {
    
    private long id;
    private long userId;
    private String username;
    private String text;
    private String title;

    private List<LikeResponse> postLikes;
    private List<CommentResponse> postComments;
    
    public PostResponse(Post post, List<LikeResponse> likes, List<CommentResponse> comments){
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.username = post.getUser().getUsername();
        this.text = post.getText();
        this.title = post.getTitle();
        this.postLikes = likes;
        this.postComments = comments;
    }

}
