package com.glory.productcatalogservice.repository;

import com.glory.productcatalogservice.entity.Category;
import com.glory.productcatalogservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategory(Category category);
}
