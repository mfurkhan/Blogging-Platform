package com.spring.blog.dto;


import com.spring.blog.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class Postdto {

    private long id;
    @NotEmpty
    @Size(min = 2, message = "Post title must have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<Comment> comments;
}
