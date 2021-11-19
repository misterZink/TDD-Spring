package com.example.tddspring;

import com.example.tddspring.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    public boolean loginUser(String username, String password) {
        if (loginService.getUsers().containsKey(username)) {
            return loginService.getUsers().get(username).getPassword().equals(password);
        }
        return false;
    }
}
