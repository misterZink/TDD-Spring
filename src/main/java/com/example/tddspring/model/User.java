package com.example.tddspring.model;

import lombok.Value;

@Value
public class User {
    String username;
    String password;
    String salt;
}
