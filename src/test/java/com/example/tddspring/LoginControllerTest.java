package com.example.tddspring;

import com.example.tddspring.enums.Permissions;
import com.example.tddspring.enums.Resource;
import com.example.tddspring.exceptions.WrongUserCredentialsException;
import com.example.tddspring.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static com.example.tddspring.utils.JwtUtil.verifyUserToken;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class LoginControllerTest {

    @Autowired
    LoginController loginController;

    @Autowired
    LoginService loginService;

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("anna", "losen", Resource.ACCOUNT, List.of(Permissions.READ)),
                Arguments.of("berit", "123456", Resource.ACCOUNT, List.of(Permissions.READ, Permissions.WRITE)),
                Arguments.of("kalle", "password", Resource.PROVISION_CALC, List.of(Permissions.EXECUTABLE))
        );
    }

    @BeforeEach
    void setUp() {

        loginService.addUser("anna", "losen");
        loginService.addUser("berit", "123456");
        loginService.addUser("kalle", "password");
    }

    @ParameterizedTest
    @MethodSource("testData")
    void test_login_user_with_encrypted_password_and_userToken_success(String username, String password) throws WrongUserCredentialsException {

        assertDoesNotThrow(() -> loginController.loginUser(username, password));
        String token = loginController.loginUser(username, password);
        assertNotEquals("", token);
    }

    @Test
    void test_login_fail_with_exception() {

        WrongUserCredentialsException thrown = assertThrows(WrongUserCredentialsException.class, () -> loginController.loginUser("wrong", "password"));
        assertEquals("login failed", thrown.getMessage());
    }

    @ParameterizedTest
    @MethodSource("testData")
    void test_verify_user_token_success(String username, String password) throws WrongUserCredentialsException {

        assertTrue(verifyUserToken(loginController.loginUser(username, password), username));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void test_user_rights_success(String username, String password, Resource resource, List<Permissions> permissions) throws WrongUserCredentialsException {

        assertDoesNotThrow(() -> loginController.loginUser(username, password));
        String token = loginController.loginUser(username, password);

        loginService.addAuthorizationsToUser(username, resource, permissions);

        assertNotNull(loginService.getUserPermissions(token, resource));
        assertFalse(loginService.getUserPermissions(token, resource).isEmpty());
    }
}
