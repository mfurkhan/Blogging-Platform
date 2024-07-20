package com.spring.blog.controller;

import com.spring.blog.dto.CategoryDto;
import com.spring.blog.dto.CategoryResponse;
import com.spring.blog.entity.Category;
import com.spring.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@Tag(
        name = "CRUD operations for Category feature"
)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Creates Comment REST API",
            description = "Create REST APi is used to save the comment details in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
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

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory (@RequestBody CategoryDto categoryDto,
                                                       @PathVariable("id") Long categoryId){
        CategoryDto category = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory (@PathVariable ("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);
    }
}
