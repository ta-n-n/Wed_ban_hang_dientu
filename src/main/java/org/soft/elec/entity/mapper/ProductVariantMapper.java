package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.response.ProductVariantResponse;
import org.soft.elec.entity.models.Product;
import org.soft.elec.entity.models.ProductVariant;

@Mapper(
    componentModel = "spring",
    uses = {ProductMapper.class})
public interface ProductVariantMapper {
  ProductVariant toEntity(ProductVariantRequest productVariantRequest);

  ProductVariantResponse toResponse(ProductVariant productVariant);

  void updateEntity(
      ProductVariantRequest productVariantRequest, @MappingTarget ProductVariant productVariant);

  default String map(Product product) {
    return product != null ? product.getName() : null;
  }
}
