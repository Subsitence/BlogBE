package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostResponseDto {
    private Integer id;
    private String name;
    private String title;
    private String body;
    private String imgUrl;
    private Date timestamp;
}
