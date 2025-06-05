package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.VariationValueRequest;
import org.soft.elec.entity.dto.response.VariationValueResponse;
import org.soft.elec.entity.models.VariationValue;

@Mapper(componentModel = "sping")
public interface VariationValueMapper {
    VariationValue toEntity(VariationValueRequest variationValueRequest);
    VariationValueResponse toResponse(VariationValue variationValue);
    void updateEntity(VariationValueRequest variationValueRequest, @MappingTarget VariationValue variationValue);
}
