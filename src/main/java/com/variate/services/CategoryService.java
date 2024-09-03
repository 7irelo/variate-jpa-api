package com.variate.services;

import com.variate.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    CategoryDto patchCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}
