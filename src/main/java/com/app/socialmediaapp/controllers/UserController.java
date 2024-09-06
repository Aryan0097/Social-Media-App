package com.app.socialmediaapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.socialmediaapp.entities.User;
import com.app.socialmediaapp.exceptions.UserNotFoundException;
import com.app.socialmediaapp.requests.UserRequest;
import com.app.socialmediaapp.responses.UserResponse;
import com.app.socialmediaapp.services.UserService;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public UserResponse createUser(@RequestBody User user) {
        System.out.println(user.getUsername());
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable long userId) {
        UserResponse userResponse=userService.getUseResponseById(userId);
        if(userResponse==null){
            throw new UserNotFoundException();
        }
        return userResponse;
    }
    
    @GetMapping("/{userId}/image")
    public ResponseEntity<byte[]> getImageByUserId(@PathVariable long userId){
    	User user = userService.getUserById(userId);
    	byte[] imageFile = user.getImageData();
    	return ResponseEntity.ok()
    			.contentType(MediaType.valueOf(user.getImageType()))
    					.body(imageFile);
    }

//     @GetMapping("/{username}")
//     public UserResponse getUserByUsername(@PathVariable String username) {
//         UserResponse userResponse=userService.getUserResponseByUsername(username);
//         if(userResponse==null){
//             throw new UserNotFoundException();
//         }
//         return userResponse;
//     }
    
    @PutMapping("/{userId}")
    public UserResponse updateUserById(@PathVariable long userId,
    			@ModelAttribute UserRequest userRequest,
    			@RequestPart MultipartFile imageFile) throws IOException {
    	return userService.updateUserById(userId,userRequest,imageFile);
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
