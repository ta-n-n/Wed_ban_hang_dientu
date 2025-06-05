package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.FileRequest;
import org.soft.elec.entity.dto.response.FileResponse;

public interface FileService {
  FileResponse createFile(FileRequest request);

  FileResponse updateFile(Integer id, FileRequest request);

  void deleteFile(Integer id);

  FileResponse getFileById(Integer id);

  List<FileResponse> getAllFiles();
}
