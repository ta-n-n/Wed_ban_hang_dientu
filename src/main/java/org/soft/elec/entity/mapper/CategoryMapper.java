package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.response.CategoryResponse;
import org.soft.elec.entity.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequest categoryRequest);
    CategoryResponse toResponse(Category category);
}
