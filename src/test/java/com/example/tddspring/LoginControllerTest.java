package com.example.tddspring;

import com.example.tddspring.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LoginControllerTest {

    @Autowired
    LoginController loginController;

    @Autowired
    LoginService loginService;


    @BeforeEach
    void setUp() {
        loginService.addUser("anna", "losen");
        loginService.addUser("berit", "123456");
        loginService.addUser("kalle", "password");
    }

    @ParameterizedTest
    @CsvSource({"anna,losen", "berit,123456", "kalle,password"})
    void test_login_user_with_encrypted_password_success(String username, String password) {

        assertTrue(loginController.loginUser(username, password));
    }
}
