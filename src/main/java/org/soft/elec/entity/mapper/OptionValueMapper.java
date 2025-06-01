package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.soft.elec.entity.dto.request.OptionRequest;
import org.soft.elec.entity.dto.response.OptionResponse;
import org.soft.elec.entity.models.Option;
import org.soft.elec.entity.models.OptionValue;

@Mapper(componentModel = "spring")
public interface OptionValueMapper {
    OptionValue toEntity(OptionRequest optionRequest);

    OptionResponse toResponse(Option option);
}
