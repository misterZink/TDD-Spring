package com.example.tddspring.services;

import com.example.tddspring.enums.Permissions;
import com.example.tddspring.enums.Resource;
import com.example.tddspring.models.User;
import com.example.tddspring.utils.PasswordUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static com.example.tddspring.utils.JwtUtil.getUsernameFromToken;

@Service
public class LoginService {

    HashMap<String, User> users = new HashMap<>();

    public void addUser(String username, String password) {
        String salt = PasswordUtils.generateSalt(512).orElse(null);
        assert salt != null;

        String hashedPassword = PasswordUtils.hashPassword(password, salt).orElse(null);
        assert hashedPassword != null;

        users.put(username, new User(username, hashedPassword, salt, new HashMap<>()));
    }

    public void addAuthorizationsToUser(String username, Resource resource, List<Permissions> permissions) {
        users.get(username).getAuthorizations().put(resource, permissions);
    }

    public List<Permissions> getUserPermissions(String token, Resource resource) {
        return users.get(getUsernameFromToken(token)).getAuthorizations().get(resource);
    }

    public HashMap<String, User> getUsers() {
        return users;
    }
}
