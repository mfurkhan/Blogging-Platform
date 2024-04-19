package com.spring.blog.controller;

import com.spring.blog.dto.CommentResponse;
import com.spring.blog.dto.CommentsDto;
import com.spring.blog.service.CommentService;
import com.spring.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    @Autowired
    CommentService commentService;

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

    @PutMapping("/{postId}/update/{id}")
    public ResponseEntity<CommentsDto> updateComment (@PathVariable("postId") long postId,
                                                      @PathVariable("id") long id,
                                                      @Valid @RequestBody CommentsDto commentsDto) {
        return new ResponseEntity<>(commentService.updateComments(commentsDto, postId, id), HttpStatus.OK);
    }

}
