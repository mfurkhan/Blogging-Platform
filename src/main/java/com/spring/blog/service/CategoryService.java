package com.spring.blog.service;

import com.spring.blog.dto.CategoryDto;
import com.spring.blog.dto.CategoryResponse;
import com.spring.blog.entity.Category;

public interface CategoryService {

    CategoryDto addCategory (CategoryDto categoryDto);

    CategoryDto getCategory (Long categoryId);

    CategoryResponse getAllCategory ();

    CategoryDto updateCategory (CategoryDto categoryDto, Long categoryId);

    void deleteCategory (Long categoryId);
}
