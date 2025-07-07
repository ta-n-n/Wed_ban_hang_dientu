package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.EntityFileRequest;
import org.soft.elec.entity.dto.request.search.EntityFileSearchRequest;
import org.soft.elec.entity.dto.response.EntityFileResponse;
import org.soft.elec.entity.mapper.EntityFileMapper;
import org.soft.elec.entity.models.EntityFile;
import org.soft.elec.entity.models.File;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.EntityFileRepository;
import org.soft.elec.repository.FileRepository;
import org.soft.elec.service.EntityFileService;
import org.soft.elec.specification.EntityFileSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EntityFileServiceImpl implements EntityFileService {

  private final EntityFileRepository entityFileRepository;
  private final FileRepository fileRepository;
  private final EntityFileMapper entityFileMapper;

  @Override
  @Transactional
  public EntityFileResponse createEntityFile(EntityFileRequest request) {
    File file =
        fileRepository
            .findById(request.getFileId())
            .orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));
    EntityFile entityFile = entityFileMapper.toEntity(request);
    entityFile.setFile(file);
    EntityFile saved = entityFileRepository.save(entityFile);
    return entityFileMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public EntityFileResponse updateEntityFile(Integer id, EntityFileRequest request) {
    EntityFile entityFile =
        entityFileRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ENTITYFILE_NOT_FOUND));
    if (request.getFileId() != null) {
      File file =
          fileRepository
              .findById(request.getFileId())
              .orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));
      entityFile.setFile(file);
    }
    entityFileMapper.updateEntity(request, entityFile);
    EntityFile updated = entityFileRepository.save(entityFile);
    return entityFileMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteEntityFile(Integer id) {
    EntityFile entityFile =
        entityFileRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ENTITYFILE_NOT_FOUND));
    entityFileRepository.delete(entityFile);
  }

  @Override
  public EntityFileResponse getEntityFileById(Integer id) {
    EntityFile entityFile =
        entityFileRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ENTITYFILE_NOT_FOUND));
    return entityFileMapper.toResponse(entityFile);
  }

  @Override
  public List<EntityFileResponse> getAllEntityFiles() {
    return entityFileRepository.findAll().stream()
        .map(entityFileMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<EntityFileResponse> searchEntityFiles(EntityFileSearchRequest request) {
    Specification<EntityFile> spec = null;
    spec = SpecUtil.add(spec, EntityFileSpecification.hasEntityId(request.getEntityId()));
    spec = SpecUtil.add(spec, EntityFileSpecification.hasEntityType(request.getEntityType()));
    spec = SpecUtil.add(spec, EntityFileSpecification.hasZone(request.getZone()));
    Pageable pageable = PageUtil.getPageable(request);
    return entityFileRepository.findAll(spec, pageable).map(entityFileMapper::toResponse);
  }
}
