package com.app.socialmediaapp.responses;

import lombok.Data;

@Data
public class AuthenticationResponse {

    String message;
    Long userId;
    String refreshToken;
    String accessToken;


}
