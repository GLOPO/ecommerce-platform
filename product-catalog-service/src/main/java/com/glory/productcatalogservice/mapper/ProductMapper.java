package com.glory.productcatalogservice.mapper;

import com.glory.productcatalogservice.dto.request.ProductRequest;
import com.glory.productcatalogservice.dto.response.ProductResponse;
import com.glory.productcatalogservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequest request);

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toResponse(Product product);
}
