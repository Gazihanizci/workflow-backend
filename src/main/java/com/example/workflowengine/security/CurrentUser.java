package com.example.workflowengine.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {

    public static Long id() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return Long.parseLong(authentication.getName());
    }
}