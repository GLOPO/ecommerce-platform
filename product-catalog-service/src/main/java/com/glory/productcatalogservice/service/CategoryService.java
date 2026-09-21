package com.glory.productcatalogservice.service;

import com.glory.productcatalogservice.dto.request.CategoryRequest;
import com.glory.productcatalogservice.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(UUID id);
}
