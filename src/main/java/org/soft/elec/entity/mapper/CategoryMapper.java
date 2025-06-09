package org.soft.elec.entity.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.response.CategoryResponse;
import org.soft.elec.entity.models.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  Category toEntity(CategoryRequest categoryRequest);

  CategoryResponse toResponse(Category category);

  void updateEntity(CategoryRequest categoryRequest, @MappingTarget Category category);

  default String mapParentName(Category parent) {
    return parent != null ? parent.getName() : null;
  }

  default List<String> mapChildrenToNames(List<Category> children) {
    return children != null
        ? children.stream().map(Category::getName).collect(Collectors.toList())
        : null;
  }
}
