package com.variate.services.impl;

import com.variate.mappers.impl.CategoryMapper;
import com.variate.model.dto.CategoryDto;
import com.variate.repositories.CategoryRepository;
import com.variate.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        var category = categoryMapper.mapFrom(categoryDto);
        return categoryMapper.mapTo(categoryRepository.save(category));
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::mapTo)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        var category = categoryMapper.mapFrom(categoryDto);
        category.setId(id);
        return categoryMapper.mapTo(categoryRepository.save(category));
    }

    @Override
    public CategoryDto patchCategory(Long id, CategoryDto categoryDto) {
        var existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (categoryDto.getName() != null) {
            existingCategory.setName(categoryDto.getName());
        }
        if (categoryDto.getDescription() != null) {
            existingCategory.setDescription(categoryDto.getDescription());
        }
        if (categoryDto.getImageUrl() != null) {
            existingCategory.setImageUrl(categoryDto.getImageUrl());
        }
        return categoryMapper.mapTo(categoryRepository.save(existingCategory));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
