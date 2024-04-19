package com.spring.blog.service.impl;

import com.spring.blog.dto.CommentResponse;
import com.spring.blog.dto.CommentsDto;
import com.spring.blog.entity.Comment;
import com.spring.blog.entity.Post;
import com.spring.blog.exception.BlogAPIException;
import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.repository.CommentRepository;
import com.spring.blog.repository.PostRepository;
import com.spring.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentsDto createComment(long postId, CommentsDto commentsDto) {

        Comment comment = mapToEntity(commentsDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        comment.setPost(post); // This defines that the comment we save is related this ({postId}) particular post id
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public CommentsDto getCommentsById(Long postId, Long id) {
//long does not allow null values where in Long allows null values
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post id not found");
        }


        return mapToDto(comment);
    }

    @Override
    public void deleteComment(Long postId, Long id) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post id not found");
        }
        commentRepository.delete(comment);
    }

    /*@Override
    public List<CommentsDto> getAllComments(long postId) {

        List<Comment> comment = commentRepository.findByPostId(postId);


        //CommentResponse response = new CommentResponse();

        return comment.stream().map(comments -> mapToDto(comments)).collect(Collectors.toList());
    }*/

    @Override
    public CommentResponse getAllComments(long postId) {

        List<Comment> comment = commentRepository.findByPostId(postId);
        List<CommentsDto> res= comment.stream().map(comments -> mapToDto(comments)).collect(Collectors.toList());
        CommentResponse response = new CommentResponse();
        response.setCommentsContent(res);
        return response;
    }

    @Override
    public CommentsDto updateComments(CommentsDto commentsDto, long postId, long id) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id));
        comment.setPost(post);
        comment.setBody(commentsDto.getBody());
        comment.setName(commentsDto.getName());
        comment.setEmail(comment.getEmail());

        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    private CommentsDto mapToDto(Comment comment) {
//        CommentsDto commentsDto1 = new CommentsDto();
//        commentsDto1.setId(comment.getId());
//        commentsDto1.setName(comment.getName());
//        commentsDto1.setEmail(comment.getEmail());
//        commentsDto1.setBody(comment.getBody());
        return modelMapper.map(comment, CommentsDto.class);
    }

    private Comment mapToEntity(CommentsDto commentsDto) {
//        Comment comment = new Comment();
//        comment.setId(commentsDto.getId());
//        comment.setName(commentsDto.getName());
//        comment.setEmail(commentsDto.getEmail());
//        comment.setBody(commentsDto.getBody());
        return modelMapper.map(commentsDto, Comment.class);
    }
}
