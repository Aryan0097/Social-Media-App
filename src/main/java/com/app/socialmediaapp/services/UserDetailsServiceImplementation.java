package com.app.socialmediaapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.socialmediaapp.entities.User;
import com.app.socialmediaapp.repository.UserRepository;
import com.app.socialmediaapp.security.JWTUserDetails;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return JWTUserDetails.create(user);  
    }

    public UserDetails loadUserById(Long id) {  
        User user = userRepository.findById(id).get();
        return JWTUserDetails.create(user);
    }
}
