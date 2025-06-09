package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.FileRequest;
import org.soft.elec.entity.dto.response.FileResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.FileMapper;
import org.soft.elec.entity.models.File;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.FileRepository;
import org.soft.elec.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileServiceImpl implements FileService {

  @Autowired private FileRepository fileRepository;

  @Autowired private FileMapper fileMapper;

  private void checkFileExist(Integer id) {
    if (!fileRepository.existsById(id)) {
      throw new AppEx(ErrorCode.FILE_NOT_FOUND);
    }
  }

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
    File file = fileRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.FILE_NOT_FOUND));
    fileMapper.updateEntity(request, file);
    File updated = fileRepository.save(file);
    return fileMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteFile(Integer id) {
    checkFileExist(id);
    fileRepository.deleteById(id);
  }

  @Override
  @Transactional
  public FileResponse getFileById(Integer id) {
    File file = fileRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.FILE_NOT_FOUND));
    return fileMapper.toResponse(file);
  }

  @Override
  public List<FileResponse> getAllFiles() {
    return fileRepository.findAll().stream()
        .map(fileMapper::toResponse)
        .collect(Collectors.toList());
  }
}
