package com.app.socialmediaapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.socialmediaapp.entities.User;
import com.app.socialmediaapp.repository.CommentRepository;
import com.app.socialmediaapp.repository.LikeRepository;
import com.app.socialmediaapp.repository.PostRepository;
import com.app.socialmediaapp.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private LikeRepository likeRepository;
    private CommentRepository commentRepository;
    private PostRepository postRepository;


    public UserService(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        // System.out.println(user.getUsername());
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        try {
            // System.out.println("Searching for user with ID: " + userId);
            return userRepository.findById(userId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            return null;
        }
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public User updateUserById(long userId, User newUser){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User updatedUser = user.get();
            updatedUser.setImage(newUser.getImage());
            updatedUser.setUsername(newUser.getUsername());
            updatedUser.setPassword(newUser.getPassword());
            userRepository.save(updatedUser);
            return updatedUser;
        }
        return null;
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

    // public List<Object> getUserActivityById(Long userId) {
    //     List<Long> postIds = postRepository.findTopByUserId(userId);
    //     if(postIds.isEmpty()) {
    //         return null;
    //     }
    //     List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
    //     List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
    //     List<Object> results = new ArrayList<>();
    //     results.addAll(comments);
    //     results.addAll(likes);
    //     return results;
    // }
}
