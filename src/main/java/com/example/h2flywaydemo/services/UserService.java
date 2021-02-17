package com.example.h2flywaydemo.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String user() {
        return System.getProperty("user.name");
    }
}
