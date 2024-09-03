package com.app.socialmediaapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.socialmediaapp.entities.Post;
import com.app.socialmediaapp.requests.CreatePostRequest;
import com.app.socialmediaapp.requests.UpdatePostRequest;
import com.app.socialmediaapp.responses.PostResponse;
import com.app.socialmediaapp.services.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/posts")
public class PostController {
    
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public PostResponse createPost(@RequestBody CreatePostRequest newPostrequest) {
        return postService.createPost(newPostrequest);
    }

    /*@GetMapping("/{postId}")  //PathVariable =>   posts/postId  
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }*/
    
    @GetMapping("/{postId}")  
    public PostResponse getPostById(@PathVariable Long postId) {
        return postService.getPostByIdWithLikesAndComments(postId);
    }

    @PutMapping("/{postId}")
    public PostResponse updatePostById(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) { 
        return postService.updatePostById(postId,updatePostRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable Long postId){
        postService.deletePostById(postId);
    }
}
