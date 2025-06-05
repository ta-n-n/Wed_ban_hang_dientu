package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.EntityFileRequest;
import org.soft.elec.entity.dto.response.EntityFileResponse;

public interface EntityFileService {
  EntityFileResponse createEntityFile(EntityFileRequest request);

  EntityFileResponse updateEntityFile(Integer id, EntityFileRequest request);

  void deleteEntityFile(Integer id);

  EntityFileResponse getEntityFileById(Integer id);

  List<EntityFileResponse> getAllEntityFiles();
}
