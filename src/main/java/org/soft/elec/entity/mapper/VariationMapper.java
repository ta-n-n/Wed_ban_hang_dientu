package org.soft.elec.entity.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.VariationRequest;
import org.soft.elec.entity.dto.response.VariationResponse;
import org.soft.elec.entity.models.Product;
import org.soft.elec.entity.models.Variation;
import org.soft.elec.entity.models.VariationValue;

@Mapper(componentModel = "spring")
public interface VariationMapper {
  Variation toEntity(VariationRequest variationRequest);

  VariationResponse toResponse(Variation variation);

  void updateEntity(VariationRequest variationRequest, @MappingTarget Variation variation);

  default List<String> mapProductsToNames(List<Product> products) {
    return products != null
        ? products.stream().map(Product::getName).collect(Collectors.toList())
        : null;
  }

  default List<String> mapVariationValuesToLabels(List<VariationValue> variationValues) {
    return variationValues != null
        ? variationValues.stream().map(VariationValue::getLabel).collect(Collectors.toList())
        : null;
  }
}
