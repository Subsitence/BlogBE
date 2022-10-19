package com.blog.repository;

import com.blog.dto.UserPostResponseDto;
import com.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT new com.blog.dto.UserPostResponseDto(p.id, u.name, p.title, p.body, p.imgUrl, p.timestamp) " +
            "FROM User u JOIN u.posts p ORDER BY p.id DESC")
    List<UserPostResponseDto> findAllPosts();

    @Query("SELECT new com.blog.dto.UserPostResponseDto(p.id, u.name, p.title, p.body, p.imgUrl, p.timestamp) " +
            "FROM User u JOIN u.posts p WHERE p.id = ?1")
    Optional<UserPostResponseDto> findPostBydId(Integer id);

    @Modifying
    @Query("DELETE FROM Post p WHERE p.id = ?1")
    void deleteById(Integer id);
}
