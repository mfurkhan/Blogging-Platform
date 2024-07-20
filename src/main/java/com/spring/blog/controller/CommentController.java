package com.spring.blog.controller;

import com.spring.blog.dto.CommentResponse;
import com.spring.blog.dto.CommentsDto;
import com.spring.blog.service.CommentService;
import com.spring.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD operations for Comment feature"
)
public class CommentController {
    @Autowired
    CommentService commentService;

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Creates Comment REST API",
            description = "Create REST APi is used to save the comment details in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommentsDto> createComments (@PathVariable("postId") long postId,
                                                       @Valid @RequestBody CommentsDto commentsDto ){
        return new ResponseEntity<>(commentService.createComment(postId,commentsDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comment/{id}")
    public ResponseEntity<CommentsDto> getCommentsById (@PathVariable("postId") Long postId,
                                                        @PathVariable("id") Long id){

        return ResponseEntity.ok(commentService.getCommentsById(postId, id));
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @DeleteMapping("/{postId}/delete/{id}")
    public ResponseEntity<String> deleteById (@PathVariable("postId") Long postId,
                              @PathVariable ("id") Long id) {
        commentService.deleteComment(postId, id);
        return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/{postId}/comment")
    public CommentResponse getAllComments (@PathVariable("postId") long postId) {
        return commentService.getAllComments(postId);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PutMapping("/{postId}/update/{id}")
    public ResponseEntity<CommentsDto> updateComment (@PathVariable("postId") long postId,
                                                      @PathVariable("id") long id,
                                                      @Valid @RequestBody CommentsDto commentsDto) {
        return new ResponseEntity<>(commentService.updateComments(commentsDto, postId, id), HttpStatus.OK);
    }

}
