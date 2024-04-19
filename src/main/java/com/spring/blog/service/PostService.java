package com.spring.blog.service;

import com.spring.blog.dto.PostResponse;
import com.spring.blog.dto.Postdto;

import java.util.List;

public interface PostService {
    Postdto createPost(Postdto postdto);

    //    List<Postdto> getAllPost(int pageNo, int pageSize);
    PostResponse getAllPost(int pageNo, int pageSize);

    Postdto getPostById(long id);

    Postdto updatePost(Postdto postdto, long id);

    void deletePost(long id);
}
