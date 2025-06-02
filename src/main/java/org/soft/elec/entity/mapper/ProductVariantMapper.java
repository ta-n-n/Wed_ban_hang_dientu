package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.response.ProductVariantResponse;
import org.soft.elec.entity.models.ProductVariant;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariant toEntity(ProductVariantRequest productVariantRequest);
    ProductVariantResponse toResponse(ProductVariant productVariant);

}
