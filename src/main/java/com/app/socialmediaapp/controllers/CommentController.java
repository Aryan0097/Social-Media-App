package com.app.socialmediaapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.socialmediaapp.entities.Comment;
import com.app.socialmediaapp.requests.CreateCommentRequest;
import com.app.socialmediaapp.requests.UpdateCommentRequest;
import com.app.socialmediaapp.responses.CommentResponse;
import com.app.socialmediaapp.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
    
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllComments(userId, postId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CreateCommentRequest createCommentRequest ){
        return commentService.createComment(createCommentRequest);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateCommentById(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest){
        return commentService.updateCommentById(commentId,updateCommentRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);
    }
}
