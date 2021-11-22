package com.example.tddspring.models;

import lombok.Value;

@Value
public class User {
    String username;
    String password;
    String salt;
}
