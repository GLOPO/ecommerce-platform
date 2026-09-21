package com.glory.productcatalogservice.service.impl;

import com.glory.productcatalogservice.dto.request.CategoryRequest;
import com.glory.productcatalogservice.dto.response.CategoryResponse;
import com.glory.productcatalogservice.entity.Category;
import com.glory.productcatalogservice.mapper.CategoryMapper;
import com.glory.productcatalogservice.repository.CategoryRepository;
import com.glory.productcatalogservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper  categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category =  categoryMapper.toEntity(request);
        Category saved = categoryRepository.save(category);

        CategoryResponse response = categoryMapper.toResponse(saved);

        return response;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category Not Found"));

        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category Not Found"));

        categoryMapper.updateEntityFromRequest(request, category);

        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    @Override
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category Not Found"));

        categoryRepository.delete(category);
    }
}
