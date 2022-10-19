package com.blog.service;

import com.blog.dto.UserPostResponseDto;
import com.blog.dto.UserPostUpdateDto;

import java.util.List;

public interface PostService {
    List<UserPostResponseDto> getAllPosts();
    UserPostResponseDto getPostById(Integer id);
    UserPostResponseDto savePost(UserPostResponseDto userPostResponseDto, String username);
    UserPostUpdateDto updatePost(UserPostUpdateDto userPostUpdateDto, Integer id, String username);
    boolean deletePost(Integer id, String username);
}
