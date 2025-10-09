package com.example.validation.service;

import com.example.validation.model.User;

import java.util.List;

public interface IUserService {
    void save(User user);
    List<User> findAll();
}
