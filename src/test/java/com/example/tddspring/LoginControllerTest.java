package com.example.tddspring;

import com.example.tddspring.Service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LoginControllerTest {

    @Autowired
    LoginController loginController;
    @Autowired
    LoginService loginService;

    @Test
    void test_login_success() {
        loginService.addUser("anna", "losen");
        loginService.addUser("berit", "123456");
        loginService.addUser("kalle", "password");

        assertTrue(loginController.loginUser("anna","losen"));
    }
}
