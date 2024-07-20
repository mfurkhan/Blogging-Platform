package com.spring.blog.dto;


import com.spring.blog.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDTO model information"
)

public class Postdto {

    private long id;
    @NotEmpty
    @Size(min = 2, message = "Post title must have at least 2 characters")
    @Schema(
            description = "Blog Post Title"
    )
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    @Schema(
            description = "Blog Post Description"
    )
    private String description;
    @NotEmpty
    @Schema(
            description = "Blog Post Content"
    )
    private String content;
    private Set<Comment> comments;
    private Long categoryId;
}
