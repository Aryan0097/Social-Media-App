package com.app.socialmediaapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.socialmediaapp.entities.Like;
import com.app.socialmediaapp.requests.CreateLikeRequest;
import com.app.socialmediaapp.responses.LikeResponse;
import com.app.socialmediaapp.services.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
    
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId) {
        return likeService.getAllLikes(userId, postId);
    }

    @PostMapping
    public Like createLike(@RequestBody CreateLikeRequest createLikeRequest) {
        return likeService.createLike(createLikeRequest);
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId) {
        return likeService.getLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLikeById(@PathVariable Long likeId) {
        likeService.deleteLikeById(likeId);
    }
}
