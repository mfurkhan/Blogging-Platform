package com.spring.blog.service.impl;

import com.spring.blog.dto.CategoryDto;
import com.spring.blog.dto.CategoryResponse;
import com.spring.blog.entity.Category;
import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.repository.CategoryRepository;
import com.spring.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("category", "id", categoryId));
        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public CategoryResponse getAllCategory() {
        List<Category> categories  = categoryRepository.findAll();
        List<CategoryDto> list = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategories(list);
        return categoryResponse;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("category", "id", categoryId));
        cat.setName(categoryDto.getName());
        cat.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(cat);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("category", "id", categoryId));
        categoryRepository.delete(cat);
    }
}
