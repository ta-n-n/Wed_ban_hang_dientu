package org.soft.elec.entity.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.response.ProductResponse;
import org.soft.elec.entity.models.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  Product toEntity(ProductRequest productRequest);

  ProductResponse toResponse(Product product);

  void updateEntity(ProductRequest productRequest, @MappingTarget Product product);

  default String mapBrandToName(Brand brand) {
    return brand != null ? brand.getName() : null;
  }

  default List<String> mapCategoriesToNames(List<Category> categories) {
    return categories != null
        ? categories.stream().map(Category::getName).collect(Collectors.toList())
        : null;
  }

  default List<String> mapVariationsToNames(List<Variation> variations) {
    return variations != null
        ? variations.stream().map(Variation::getName).collect(Collectors.toList())
        : null;
  }

  default List<String> mapOptionsToNames(List<Option> options) {
    return options != null
        ? options.stream().map(Option::getType).collect(Collectors.toList())
        : null;
  }

  default List<String> mapVariantsToNames(List<ProductVariant> variants) {
    return variants != null
        ? variants.stream().map(ProductVariant::getName).collect(Collectors.toList())
        : null;
  }
}
