package com.example.tddspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LoginControllerTest {
    @Autowired
    LoginController loginController;

    @Test
    void test_login_success() {

        assertTrue(loginController.loginUser("",""));
    }
}
