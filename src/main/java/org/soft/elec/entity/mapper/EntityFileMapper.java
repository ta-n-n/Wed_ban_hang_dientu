package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.soft.elec.entity.dto.request.EntityFileRequest;
import org.soft.elec.entity.dto.response.EntityFileResponse;
import org.soft.elec.entity.models.EntityFile;

@Mapper(componentModel = "spring")
public interface EntityFileMapper {
    EntityFile toEntity(EntityFileRequest entityFileRequest);

    EntityFileResponse toResponse(EntityFile entityFile);

}

