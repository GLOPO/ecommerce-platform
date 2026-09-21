package com.glory.productcatalogservice.mapper;

import com.glory.productcatalogservice.dto.request.CategoryRequest;
import com.glory.productcatalogservice.dto.response.CategoryResponse;
import com.glory.productcatalogservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);
    CategoryResponse toResponse(Category category);

    void updateEntityFromRequest(CategoryRequest request, @MappingTarget Category category);
}
