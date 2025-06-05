package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.OrderProductRequest;
import org.soft.elec.entity.dto.response.OrderProductResponse;
import org.soft.elec.entity.models.OrderProduct;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
  OrderProduct toEntity(OrderProductRequest orderProductRequest);

  OrderProductResponse toResponse(OrderProduct orderProduct);

  void updateEntity(
      OrderProductRequest orderProductRequest, @MappingTarget OrderProduct orderProduct);
}
