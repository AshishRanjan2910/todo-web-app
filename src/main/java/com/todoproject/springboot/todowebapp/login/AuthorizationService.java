package com.todoproject.springboot.todowebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    public boolean authenticate(String username, String password) {

        boolean isValidUserName = username.equalsIgnoreCase("user");
        boolean isValidPassword = password.equalsIgnoreCase("user123");

        return isValidUserName && isValidPassword;
    }
}
