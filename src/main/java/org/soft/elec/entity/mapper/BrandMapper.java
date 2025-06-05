package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.BrandRequest;
import org.soft.elec.entity.dto.response.BrandResponse;
import org.soft.elec.entity.models.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {
  Brand toEntity(BrandRequest brandRequest);

  BrandResponse toResponse(Brand brand);

  void updateEntity(BrandRequest brandRequest, @MappingTarget Brand brand);
}
