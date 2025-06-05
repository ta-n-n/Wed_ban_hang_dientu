package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.VariationRequest;
import org.soft.elec.entity.dto.response.VariationResponse;
import org.soft.elec.entity.models.Variation;

@Mapper(componentModel = "spring")
public interface VariationMapper {
  Variation toEntity(VariationRequest variationRequest);

  VariationResponse toResponse(Variation variation);

  void updateEntity(VariationRequest variationRequest, @MappingTarget Variation variation);
}
