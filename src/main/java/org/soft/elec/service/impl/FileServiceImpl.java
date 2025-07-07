package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.FileRequest;
import org.soft.elec.entity.dto.request.search.FileSearchRequest;
import org.soft.elec.entity.dto.response.FileResponse;
import org.soft.elec.entity.mapper.FileMapper;
import org.soft.elec.entity.models.File;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.FileRepository;
import org.soft.elec.service.FileService;
import org.soft.elec.specification.FileSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

  private final FileRepository fileRepository;
  private final FileMapper fileMapper;

  @Override
  @Transactional
  public FileResponse createFile(FileRequest request) {
    File file = fileMapper.toEntity(request);
    File saved = fileRepository.save(file);
    return fileMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public FileResponse updateFile(Integer id, FileRequest request) {
    File file =
        fileRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));
    fileMapper.updateEntity(request, file);
    File updated = fileRepository.save(file);
    return fileMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteFile(Integer id) {
    File file =
        fileRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));
    fileRepository.delete(file);
  }

  @Override
  public FileResponse getFileById(Integer id) {
    File file =
        fileRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));
    return fileMapper.toResponse(file);
  }

  @Override
  public List<FileResponse> getAllFiles() {
    return fileRepository.findAll().stream()
        .map(fileMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<FileResponse> searchFiles(FileSearchRequest request) {
    Specification<File> spec = null;
    spec = SpecUtil.add(spec, FileSpecification.hasUserId(request.getUserId()));
    spec = SpecUtil.add(spec, FileSpecification.hasFilename(request.getFilename()));
    spec = SpecUtil.add(spec, FileSpecification.hasMime(request.getMime()));
    spec = SpecUtil.add(spec, FileSpecification.hasExtension(request.getExtension()));
    Pageable pageable = PageUtil.getPageable(request);
    return fileRepository.findAll(spec, pageable).map(fileMapper::toResponse);
  }
}
