package com.spring.blog.service;

import com.spring.blog.dto.CommentResponse;
import com.spring.blog.dto.CommentsDto;

import java.util.List;

public interface CommentService {

    CommentsDto createComment (long postId, CommentsDto commentsDto);

    CommentsDto getCommentsById (Long postId, Long id);

    void deleteComment (Long postId, Long id);

    //List<CommentsDto> getAllComments (long postId);
    CommentResponse getAllComments (long postId);

    CommentsDto updateComments (CommentsDto commentsDto, long postId, long id);
}
