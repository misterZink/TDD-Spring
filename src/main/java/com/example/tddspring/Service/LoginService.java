package com.example.tddspring.Service;

import com.example.tddspring.Model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {
    HashMap<String, User> users = new HashMap<>();

    public void addUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    public HashMap<String, User> getUsers() {
        return users;
    }
}
