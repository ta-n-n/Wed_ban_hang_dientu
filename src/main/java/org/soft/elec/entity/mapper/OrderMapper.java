package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.OrderRequest;
import org.soft.elec.entity.dto.response.OrderResponse;
import org.soft.elec.entity.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequest orderRequest);
    OrderResponse toResponse(Order order);
    void updateEntity(OrderRequest orderRequest, @MappingTarget Order order);
}
