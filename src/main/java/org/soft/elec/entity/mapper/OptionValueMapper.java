package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.OptionValueRequest;
import org.soft.elec.entity.dto.response.OptionValueResponse;
import org.soft.elec.entity.models.OptionValue;

@Mapper(componentModel = "spring")
public interface OptionValueMapper {
    OptionValue toEntity(OptionValueRequest optionValueRequest);
    OptionValueResponse toResponse(OptionValue optionValue);
    void updateEntity(OptionValueRequest optionValueRequest, @MappingTarget OptionValue optionValue);
}
