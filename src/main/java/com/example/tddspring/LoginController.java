package com.example.tddspring;

import com.example.tddspring.model.User;
import com.example.tddspring.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static com.example.tddspring.util.PasswordUtils.verifyPassword;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    public boolean loginUser(String username, String password) {
        User user = loginService.getUsers().get(username);
        if (user != null) {
            return verifyPassword(password, user.getPassword(), user.getSalt());
        }
        return false;
    }
}
