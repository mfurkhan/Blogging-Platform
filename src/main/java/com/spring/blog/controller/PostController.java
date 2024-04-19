package com.spring.blog.controller;

import com.spring.blog.dto.PostResponse;
import com.spring.blog.dto.Postdto;
import com.spring.blog.entity.Post;
import com.spring.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Postdto> createPost (@Valid @RequestBody Postdto postdto) {
        return new ResponseEntity<>(postService.createPost(postdto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPost(
            @RequestParam (value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam (value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return postService.getAllPost(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postdto> getPostById(@PathVariable( "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Postdto> updatePost (@RequestBody Postdto postdto, @PathVariable(name = "id") long id){
       Postdto resp= postService.updatePost(postdto,id);
       return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost (@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted Successfully", HttpStatus.OK);
    }

}
