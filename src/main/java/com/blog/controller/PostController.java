package com.blog.controller;

import com.blog.dto.StatusResponseDto;
import com.blog.dto.UserPostResponseDto;
import com.blog.dto.UserPostUpdateDto;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")      // localhost:8080/api/post
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping()
    public List<UserPostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPostResponseDto> getPost(@PathVariable("id") Integer id) {
        UserPostResponseDto post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserPostResponseDto> savePost(@RequestBody UserPostResponseDto userPostResponseDto,
                                                        Authentication authentication) {
        return new ResponseEntity<>(postService.savePost(userPostResponseDto, authentication.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponseDto> updatePost(@PathVariable("id") Integer id,
                                                        @RequestBody UserPostUpdateDto userPostUpdateDto,
                                                        Authentication authentication)
    {
        UserPostUpdateDto updatePost = postService.updatePost(userPostUpdateDto, id, authentication.getName());
        if (updatePost == null) {
            return new ResponseEntity<>(new StatusResponseDto("Don't have permission!"), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(new StatusResponseDto("Update success!"), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusResponseDto> deletePost(@PathVariable("id") Integer id,
                                             Authentication authentication)
    {
        boolean delete = postService.deletePost(id, authentication.getName());
        if (!delete) {
            return new ResponseEntity<>(new StatusResponseDto("Don't have permission!"), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(new StatusResponseDto("Delete success!"), HttpStatus.OK);
        }
    }
}
