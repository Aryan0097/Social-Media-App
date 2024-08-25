package com.app.socialmediaapp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.socialmediaapp.entities.Comment;
import com.app.socialmediaapp.entities.Post;
import com.app.socialmediaapp.entities.User;
import com.app.socialmediaapp.repository.CommentRepository;
import com.app.socialmediaapp.requests.CreateCommentRequest;
import com.app.socialmediaapp.requests.UpdateCommentRequest;
import com.app.socialmediaapp.responses.CommentResponse;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<CommentResponse> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()) {
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());  
        }else if(userId.isPresent()){  
            comments =  commentRepository.findByUserId(userId.get());
        }else if (postId.isPresent()){  
            comments = commentRepository.findByPostId(postId.get());
        }else
            comments =  commentRepository.findAll();  
        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CreateCommentRequest createCommentRequest) {
        User user = userService.getUserById(createCommentRequest.getUserId());
        Post post = postService.getPostById(createCommentRequest.getPostId());
        if(user != null && post !=null) {
            Comment comment = new Comment();
            comment.setId(createCommentRequest.getId());
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(createCommentRequest.getText());
            comment.setCreateDate(new Date());
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment updateCommentById(Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        Optional <Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment updateComment = comment.get();
            updateComment.setText(updateCommentRequest.getText());
            return commentRepository.save(updateComment);
        }else
            return null;
    }

    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
