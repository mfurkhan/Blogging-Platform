package com.spring.blog.service;

import com.spring.blog.dto.PostResponse;
import com.spring.blog.dto.PostResponseById;
import com.spring.blog.dto.Postdto;
import com.spring.blog.entity.Post;

import java.util.List;

public interface PostService {
    Postdto createPost(Postdto postdto);

    //    List<Postdto> getAllPost(int pageNo, int pageSize);
    PostResponse getAllPost(int pageNo, int pageSize);

    PostResponseById getPostById(long id);

    Postdto updatePost(Postdto postdto, long id);

    void deletePost(long id);

    List<Postdto> getPostByCategory (Long categoryId);
}
