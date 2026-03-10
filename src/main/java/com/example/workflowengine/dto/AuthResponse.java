package com.example.workflowengine.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private boolean success;
    private String message;
    private String accessToken;
    private String refreshToken;

}