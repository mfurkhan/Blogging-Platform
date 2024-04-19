package com.spring.blog.dto;

import com.spring.blog.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private List<CommentsDto> commentsContent;
//    private int pageNo;
//    private int pageSize;
//    private int totalElements;
//    private int totalPages;
//    private boolean last;
}
