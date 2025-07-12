package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.EntityFileRequest;
import org.soft.elec.entity.dto.response.EntityFileResponse;
import org.soft.elec.entity.models.EntityFile;

@Mapper(componentModel = "spring")
public interface EntityFileMapper {
  EntityFile toEntity(EntityFileRequest entityFileRequest);

  //  @Mapping(target = "fileId", source = "file.id")
  //  @Mapping(target = "fileName", source = "file.filename")
  EntityFileResponse toResponse(EntityFile entityFile);

  void updateEntity(EntityFileRequest entityFileRequest, @MappingTarget EntityFile entityFile);
}
