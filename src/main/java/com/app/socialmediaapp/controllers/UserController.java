package com.app.socialmediaapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.socialmediaapp.entities.User;
import com.app.socialmediaapp.exceptions.UserNotFoundException;
import com.app.socialmediaapp.responses.UserResponse;
import com.app.socialmediaapp.services.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println(user.getUsername());
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable long userId) {
        User user=userService.getUserById(userId);
        if(user==null){
            throw new UserNotFoundException();
        }
        return new UserResponse(user);
    }

    // @GetMapping("/{username}")
    // public User getUserByUsername(@PathVariable String username) {
    //     User user=userService.getUserByUsername(username);
    //     if(user==null){
    //         throw new UserNotFoundException();
    //     }
    //     return user;
    // }
    
    @PutMapping("/{userId}")
    public User updateUserById(@PathVariable long userId, @RequestBody User user) {
        return userService.updateUserById(userId,user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable long userId){
        userService.deleteUserById(userId);
    }
    
    // @GetMapping("/activity/{userId}")
    // public List<Object> getUserActivityById(@PathVariable Long userId){
    //     return userService.getUserActivityById(userId);
    // }

    /*@ResponseBody*/
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleUserNotFoundException(){ 

    }
    
}
