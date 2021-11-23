package com.example.tddspring.models;

import com.example.tddspring.enums.Permissions;
import com.example.tddspring.enums.Resource;
import lombok.Value;

import java.util.HashMap;
import java.util.List;


@Value
public class User {
    String username;
    String password;
    String salt;
    HashMap<Resource, List<Permissions>> authorizations;
}
