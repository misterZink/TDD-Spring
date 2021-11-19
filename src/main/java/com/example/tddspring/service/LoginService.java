package com.example.tddspring.service;

import com.example.tddspring.model.User;
import com.example.tddspring.util.PasswordUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {
    HashMap<String, User> users = new HashMap<>();

    public void addUser(String username, String password) {
        String salt = PasswordUtils.generateSalt(512).orElse(null);
        assert salt != null;

        String hashedPassword = PasswordUtils.hashPassword(password, salt).orElse(null);
        assert hashedPassword != null;

        users.put(username,new User(username, hashedPassword, salt));
    }

    public HashMap<String, User> getUsers() {
        return users;
    }
}
