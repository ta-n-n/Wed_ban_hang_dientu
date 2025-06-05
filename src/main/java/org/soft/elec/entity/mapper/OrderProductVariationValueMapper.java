package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.OrderProductVariationValueRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationValueResponse;
import org.soft.elec.entity.models.OrderProductVariationValue;

@Mapper(componentModel = "spring")
public interface OrderProductVariationValueMapper {
  OrderProductVariationValue toEntity(
      OrderProductVariationValueRequest orderProductVariationValueRequest);

  OrderProductVariationValueResponse toResponse(
      OrderProductVariationValue orderProductVariationValue);

  void updateEntity(
      OrderProductVariationValueRequest orderProductVariationValueRequest,
      @MappingTarget OrderProductVariationValue orderProductVariationValue);
}
