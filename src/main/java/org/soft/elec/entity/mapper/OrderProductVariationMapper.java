package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.OrderProductVariationRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationResponse;
import org.soft.elec.entity.models.OrderProductVariation;

@Mapper(componentModel = "spring")
public interface OrderProductVariationMapper {
  OrderProductVariation toEntity(OrderProductVariationRequest orderProductVariationRequest);

  OrderProductVariationResponse toResponse(OrderProductVariation orderProductVariation);

  void updateEntity(
      OrderProductVariationRequest orderProductVariationRequest,
      @MappingTarget OrderProductVariation orderProductVariation);
}
