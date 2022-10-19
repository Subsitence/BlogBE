package com.blog.service;

import com.blog.dto.UserRegistrationDto;
import com.blog.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(UserRegistrationDto userRegistrationDto);
    User updateUser(User user);
    void deleteUser(User user);
}
