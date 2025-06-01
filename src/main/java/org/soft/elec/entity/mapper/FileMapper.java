package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.soft.elec.entity.dto.request.FileRequest;
import org.soft.elec.entity.dto.response.FileResponse;
import org.soft.elec.entity.models.File;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File toEntity(FileRequest fileRequest);

    FileResponse toResponse(File file);
}
