package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.OrderProductRequest;
import org.soft.elec.entity.dto.response.OrderProductResponse;
import org.soft.elec.entity.models.OrderProduct;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
  OrderProduct toEntity(OrderProductRequest orderProductRequest);

  @Mapping(source = "order.id", target = "order")
  @Mapping(source = "product.name", target = "product")
  @Mapping(source = "productVariant.name", target = "productVariant")
  OrderProductResponse toResponse(OrderProduct orderProduct);

  void updateEntity(
      OrderProductRequest orderProductRequest, @MappingTarget OrderProduct orderProduct);
}
