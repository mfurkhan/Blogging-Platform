package com.spring.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponseById {
    private List<Postdto> content;
}
