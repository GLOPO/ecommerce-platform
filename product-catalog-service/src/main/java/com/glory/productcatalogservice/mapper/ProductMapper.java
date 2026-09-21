package com.glory.productcatalogservice.mapper;

import com.glory.productcatalogservice.dto.request.CategoryRequest;
import com.glory.productcatalogservice.dto.request.ProductRequest;
import com.glory.productcatalogservice.dto.response.ProductResponse;
import com.glory.productcatalogservice.entity.Category;
import com.glory.productcatalogservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequest request);

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toResponse(Product product);

    @Mapping(target = "category", ignore = true)
    void updateEntityFromRequest(ProductRequest request, @MappingTarget Product product);
}
