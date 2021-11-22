package com.example.tddspring;

import com.example.tddspring.exceptions.WrongUserCredentialsException;
import com.example.tddspring.models.User;
import com.example.tddspring.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static com.example.tddspring.utils.PasswordUtils.verifyPassword;
import static com.example.tddspring.utils.JwtUtil.*;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    public String loginUser(String username, String password) throws WrongUserCredentialsException {
        User user = loginService.getUsers().get(username);
        if (user == null || !verifyPassword(password, user.getPassword(), user.getSalt())) {
            throw new WrongUserCredentialsException("login failed");
        }
        return createUserToken(user.getUsername());
    }
}
