package com.variate.controllers;

import com.variate.mappers.Mapper;
import com.variate.model.entities.Category;
import com.variate.model.dto.CategoryDto;
import com.variate.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    private Mapper<Category, CategoryDto> categoryMapper;

    public CategoryController(CategoryService categoryService, Mapper<Category, CategoryDto> categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping(path = "api/categories")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.mapFrom(categoryDto);
        Category savedCategory = categoryService.createCategory(category);
        return categoryMapper.mapTo(savedCategory);
    }
}
