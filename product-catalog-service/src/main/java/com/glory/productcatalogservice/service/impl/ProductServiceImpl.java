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

import java.util.List;
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

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(UUID id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        productMapper.updateEntityFromRequest(request, product);

        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        productRepository.delete(product);
    }
}
