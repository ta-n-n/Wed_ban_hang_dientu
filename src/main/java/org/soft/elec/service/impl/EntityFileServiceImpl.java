package org.soft.elec.service.impl;

import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.EntityFileRequest;
import org.soft.elec.entity.dto.response.EntityFileResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.EntityFileMapper;
import org.soft.elec.entity.models.EntityFile;
import org.soft.elec.entity.models.File;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.EntityFileRepository;
import org.soft.elec.repository.FileRepository;
import org.soft.elec.service.EntityFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntityFileServiceImpl implements EntityFileService {

    private final EntityFileRepository entityFileRepository;
    private final FileRepository fileRepository;
    private final EntityFileMapper entityFileMapper;

    private void checkEntityFileExist(Integer id) {
        if (!entityFileRepository.existsById(id)) {
            throw new AppEx(ErrorCode.ENTITYFILE_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional
    public EntityFileResponse createEntityFile(EntityFileRequest request) {
        File file = fileRepository.findById(request.getFileId())
                .orElseThrow(() -> new AppEx(ErrorCode.FILE_NOT_FOUND));
        EntityFile entityFile = entityFileMapper.toEntity(request);
        entityFile.setFile(file);
        EntityFile saved = entityFileRepository.save(entityFile);
        return entityFileMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public EntityFileResponse updateEntityFile(Integer id, EntityFileRequest request) {
        EntityFile entityFile = entityFileRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.ENTITYFILE_NOT_FOUND));
        if (request.getFileId() != null) {
            File file = fileRepository.findById(request.getFileId())
                    .orElseThrow(() -> new AppEx(ErrorCode.FILE_NOT_FOUND));
            entityFile.setFile(file);
        }
        entityFileMapper.updateEntity(request, entityFile);
        EntityFile updated = entityFileRepository.save(entityFile);
        return entityFileMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteEntityFile(Integer id) {
        checkEntityFileExist(id);
        entityFileRepository.deleteById(id);
    }

    @Override
    @Transactional
    public  EntityFileResponse getEntityFileById(Integer id) {
        EntityFile entityFile = entityFileRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.ENTITYFILE_NOT_FOUND));
        return entityFileMapper.toResponse(entityFile);
    }

    @Override
    public List<EntityFileResponse> getAllEntityFiles() {
        return entityFileRepository.findAll()
                .stream()
                .map(entityFileMapper::toResponse)
                .collect(Collectors.toList());
    }
}
