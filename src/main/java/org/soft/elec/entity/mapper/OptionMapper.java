package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.OptionRequest;
import org.soft.elec.entity.dto.response.OptionResponse;
import org.soft.elec.entity.models.Option;

@Mapper(componentModel = "spring")
public interface OptionMapper {
    Option toEntity(OptionRequest optionRequest);
    OptionResponse toResponse(Option option);
    void updateEntity(OptionRequest optionRequest, @MappingTarget Option option);
}
