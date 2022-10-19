package com.blog.service.impl;

import com.blog.dto.UserPostResponseDto;
import com.blog.dto.UserPostUpdateDto;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserPostResponseDto> getAllPosts() {
        return postRepository.findAllPosts();
    }

    @Override
    public UserPostResponseDto getPostById(Integer id) {
        return postRepository.findPostBydId(id)
                .orElse(null);
    }

    @Override
    public UserPostResponseDto savePost(UserPostResponseDto userPostResponseDto, String username) {
        User user = userRepository.findByUsername(username);
        if (username == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        Post post = new Post();
        post.setBody(userPostResponseDto.getBody());
        post.setTitle(userPostResponseDto.getTitle());
        post.setImgUrl(userPostResponseDto.getImgUrl());
        user.getPosts().add(post);
        userRepository.save(user);

        return userPostResponseDto;
    }

    @Override
    public UserPostUpdateDto updatePost(UserPostUpdateDto userPostUpdateDto, Integer id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (user.getPosts().contains(post)) {
            post.setTitle(userPostUpdateDto.getTitle());
            post.setBody(userPostUpdateDto.getBody());
            postRepository.save(post);
            return userPostUpdateDto;
        }
        return null;
    }

    @Override
    public boolean deletePost(Integer id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (user.getPosts().contains(post)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
