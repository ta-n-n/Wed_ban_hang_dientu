package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.OptionValueRequest;
import org.soft.elec.entity.dto.response.OptionValueResponse;
import org.soft.elec.entity.models.OptionValue;

@Mapper(componentModel = "spring")
public interface OptionValueMapper {
  @Mapping(target = "option", source = "option.type")
  OptionValueResponse toResponse(OptionValue optionValue);

  OptionValue toEntity(OptionValueRequest request);

  void updateEntity(OptionValueRequest request, @MappingTarget OptionValue entity);
}
