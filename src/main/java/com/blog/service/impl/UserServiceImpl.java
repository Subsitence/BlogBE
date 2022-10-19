package com.blog.service.impl;

import com.blog.dto.UserRegistrationDto;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getUsername(),
                        encoder.encode(userRegistrationDto.getPassword()), userRegistrationDto.getName());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }
}
