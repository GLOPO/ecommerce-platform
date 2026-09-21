package com.glory.productcatalogservice.service.impl;

import com.glory.productcatalogservice.dto.request.ProductRequest;
import com.glory.productcatalogservice.dto.response.ProductResponse;
import com.glory.productcatalogservice.entity.Category;
import com.glory.productcatalogservice.entity.Product;
import com.glory.productcatalogservice.mapper.ProductMapper;
import com.glory.productcatalogservice.repository.CategoryRepository;
import com.glory.productcatalogservice.repository.ProductRepository;
import com.glory.productcatalogservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final CategoryRepository  categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product product = productMapper.toEntity(request);
        product.setCategory(category);
        Product saved = productRepository.save(product);

        return productMapper.toResponse(saved);
    }
}
