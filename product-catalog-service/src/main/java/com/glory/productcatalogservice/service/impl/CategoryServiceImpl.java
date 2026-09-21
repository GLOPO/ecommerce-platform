package com.glory.productcatalogservice.service.impl;

import com.glory.productcatalogservice.dto.request.CategoryRequest;
import com.glory.productcatalogservice.dto.response.CategoryResponse;
import com.glory.productcatalogservice.entity.Category;
import com.glory.productcatalogservice.mapper.CategoryMapper;
import com.glory.productcatalogservice.repository.CategoryRepository;
import com.glory.productcatalogservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
