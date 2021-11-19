package com.example.tddspring;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    public boolean loginUser(String username, String password) {
        return true;
    }
}
