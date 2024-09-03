package com.app.socialmediaapp.services;

import java.util.Date;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.app.socialmediaapp.entities.Post;
import com.app.socialmediaapp.entities.User;
import com.app.socialmediaapp.repository.PostRepository;
import com.app.socialmediaapp.requests.CreatePostRequest;
import com.app.socialmediaapp.requests.UpdatePostRequest;
import com.app.socialmediaapp.responses.CommentResponse;
import com.app.socialmediaapp.responses.LikeResponse;
import com.app.socialmediaapp.responses.PostResponse;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;
    private CommentService commentService;


    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Autowired
    public void setLikeService(@Lazy LikeService likeService) {
        this.likeService = likeService;
    }

    @Autowired
    public void setCommentService(@Lazy CommentService commentService) {
        this.commentService = commentService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {                      
        List<Post> postList;
        if (userId.isPresent()) {                                                       
            postList = postRepository.findByUserId(userId.get());
        }else{
            postList = postRepository.findAll();                                         
        }
        return postList.stream().map(post -> {
            List<LikeResponse> likes = likeService.getAllLikes(Optional.ofNullable(null),Optional.of(post.getId()));
            List<CommentResponse> comments = commentService.getAllComments(Optional.ofNullable(null), Optional.of(post.getId()));
            return new PostResponse(post,likes,comments);}).collect(Collectors.toList());       
    }
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
    public PostResponse getPostByIdWithLikesAndComments(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        List<LikeResponse> likes = likeService.getAllLikes(Optional.ofNullable(null),Optional.of(postId));
        List<CommentResponse> comments = commentService.getAllComments(Optional.ofNullable(null), Optional.of(post.getId()));
        return new PostResponse(post,likes,comments);
    }

    public PostResponse createPost(CreatePostRequest newPostRequest) {
        User user = userService.getUserById(newPostRequest.getUserId());           
        if(user==null){
            return null;
        }
        Post post = new Post();
        post.setId(newPostRequest.getId());
        post.setText(newPostRequest.getText());
        post.setTitle(newPostRequest.getTitle());
        post.setUser(user);
        post.setCreateDate(new Date());
        System.out.println(newPostRequest.getTitle());
        postRepository.save(post);
        
        return new PostResponse(post, null, null);
    }

    public PostResponse updatePostById(Long postId, UpdatePostRequest updatePostRequest) {  
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post updatePost = post.get();
            updatePost.setText(updatePostRequest.getText());
            updatePost.setTitle(updatePostRequest.getTitle());
            postRepository.save(updatePost);
            List<LikeResponse> likes = likeService.getAllLikes(Optional.ofNullable(null),Optional.of(postId));
            List<CommentResponse> comments = commentService.getAllComments(Optional.ofNullable(null), Optional.of(postId));
            return new PostResponse(updatePost, likes, comments);
        }
        return null;
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
