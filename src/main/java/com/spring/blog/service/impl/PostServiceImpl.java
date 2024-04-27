package com.spring.blog.service.impl;

import com.spring.blog.dto.PostResponse;
import com.spring.blog.dto.PostResponseById;
import com.spring.blog.dto.Postdto;
import com.spring.blog.entity.Post;
import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.repository.PostRepository;
import com.spring.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Postdto createPost(Postdto postdto) {


        Post post = mapToEntity(postdto);
        Post newPost = postRepository.save(post);
        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPost = posts.getContent();
        List<Postdto> content = listOfPost.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getNumberOfElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostResponseById getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        Postdto content = mapToDTO(post);
        List<Postdto> postdtos = new ArrayList<>();
        postdtos.add(content);
        PostResponseById response = new PostResponseById();
        response.setContent(postdtos);
        return response;
    }

    @Override
    public Postdto updatePost(Postdto postdto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    //converting entity to DTO
    private Postdto mapToDTO(Post post) {
        Postdto postdto = modelMapper.map(post, Postdto.class); //Either do like this and return the value or do like the one showed in the method below

//        Postdto postDto = new Postdto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postdto;
    }

/*ModelMapper is a library that automatically maps fields from one object to another based on their names and types.*/


    //converting DTO to entity
    private Post mapToEntity(Postdto postdto) {
        //        Post post = new Post();
//        post.setTitle(postdto.getTitle());
//        post.setDescription(postdto.getDescription());
//        post.setContent(postdto.getContent());
        return modelMapper.map(postdto, Post.class);
    }
}
