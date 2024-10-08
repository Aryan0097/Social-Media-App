package com.app.socialmediaapp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.socialmediaapp.entities.User;
import com.app.socialmediaapp.exceptions.UserNotFoundException;
import com.app.socialmediaapp.repository.CommentRepository;
import com.app.socialmediaapp.repository.LikeRepository;
import com.app.socialmediaapp.repository.PostRepository;
import com.app.socialmediaapp.repository.UserRepository;
import com.app.socialmediaapp.requests.UserRequest;
import com.app.socialmediaapp.responses.UserResponse;

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

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(user -> new UserResponse(
                user
            ))
            .collect(Collectors.toList());
    }

    public UserResponse createUser(User user){
        // System.out.println(user.getUsername());
        return new UserResponse(userRepository.save(user));
    }
    
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found with given userid:"+userId));
    }

    public UserResponse getUseResponseById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found with given userid:"+userId));
        return new UserResponse(user);
    }
    
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserResponse getUserResponseByUsername(String username){
        return new UserResponse(userRepository.findByUsername(username));
    }

    public UserResponse updateUserById(long userId, UserRequest newUser, MultipartFile imageFile) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User updatedUser = user.get();

            updatedUser.setUsername(newUser.getUsername());
            userRepository.save(updatedUser);
            return new UserResponse(updatedUser);
        }
        return null;
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }
    
    public void followUser(Long followee, Long follower) {
    	User followToUser = userRepository.findById(followee).orElseThrow(()-> new UserNotFoundException());
    	User followerUser = userRepository.findById(follower).orElseThrow(()-> new UserNotFoundException());
    	
    	followToUser.getFollowers().add(followerUser);
    	followerUser.getFollowing().add(followToUser);
    	
    	userRepository.save(followToUser);
    	userRepository.save(followerUser);
    }
    
    public void unfollowUser(Long followee, Long follower) {
    	User followToUser = userRepository.findById(followee).orElseThrow(()-> new UserNotFoundException());
    	User followerUser = userRepository.findById(follower).orElseThrow(()-> new UserNotFoundException());
    	
    	followToUser.getFollowers().remove(followerUser);
    	followerUser.getFollowing().remove(followToUser);
    	
    	userRepository.save(followToUser);
    	userRepository.save(followerUser);
    }
    
    public List<UserResponse> getFollowers(Long userId) {
        return userRepository.findFollowersByUserId(userId);
    }

    public List<UserResponse> getFollowing(Long userId) {
        return userRepository.findFollowingByUserId(userId);
    }
    
    public List<UserResponse> searchUsers(String searchString) {
        return userRepository.searchUsersByUsername(searchString);
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
