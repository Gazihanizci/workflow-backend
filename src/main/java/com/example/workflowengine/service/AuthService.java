package com.example.workflowengine.service;

import com.example.workflowengine.dto.AuthResponse;
import com.example.workflowengine.dto.LoginRequest;
import com.example.workflowengine.entity.User;
import com.example.workflowengine.repository.UserRepository;
import com.example.workflowengine.security.JwtService;
import com.example.workflowengine.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request){

        AuthResponse response = new AuthResponse();

        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if(user == null){
            response.setSuccess(false);
            response.setMessage("Kullanıcı bulunamadı");
            return response;
        }

        String hash = HashUtil.sha256(request.getPassword());

        if(!hash.equals(user.getParolaHash())){
            response.setSuccess(false);
            response.setMessage("Parola yanlış");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Giriş başarılı");

        response.setAccessToken(
                jwtService.generateAccessToken(
                        user.getId(),
                        user.getEmail()
                )
        );

        response.setRefreshToken(
                jwtService.generateRefreshToken(
                        user.getId(),
                        user.getEmail()
                )
        );

        return response;
    }
}