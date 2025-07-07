package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.VariationValueRequest;
import org.soft.elec.entity.dto.response.VariationValueResponse;
import org.soft.elec.entity.models.VariationValue;

@Mapper(componentModel = "spring")
public interface VariationValueMapper {
  @Mapping(target = "variationId", source = "variation.id")
  @Mapping(target = "variationName", source = "variation.name")
  VariationValueResponse toResponse(VariationValue variationValue);

  VariationValue toEntity(VariationValueRequest variationValueRequest);

  void updateEntity(
      VariationValueRequest variationValueRequest, @MappingTarget VariationValue variationValue);
}
