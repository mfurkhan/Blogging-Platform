package com.spring.blog.controller;

import com.spring.blog.dto.CategoryDto;
import com.spring.blog.dto.CategoryResponse;
import com.spring.blog.entity.Category;
import com.spring.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory (@RequestBody CategoryDto categoryDto) {

        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping (value = "{id}")
    public  ResponseEntity<CategoryDto> getCategory (@PathVariable("id") Long categoryId){
        return  ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @GetMapping
    public CategoryResponse getAllCategories (){
        return categoryService.getAllCategory();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory (@RequestBody CategoryDto categoryDto,
                                                       @PathVariable("id") Long categoryId){
        CategoryDto category = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory (@PathVariable ("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);
    }
}
