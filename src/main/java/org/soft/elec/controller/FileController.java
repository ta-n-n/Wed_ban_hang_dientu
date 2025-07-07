package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.FileRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.FileResponse;
import org.soft.elec.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

  private final FileService fileService;

  @PostMapping
  public ResponseEntity<ApiResponse<FileResponse>> create(@Valid @RequestBody FileRequest request) {
    FileResponse created = fileService.createFile(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<FileResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody FileRequest request) {
    FileResponse updated = fileService.updateFile(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    fileService.deleteFile(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<FileResponse>> getById(@PathVariable Integer id) {
    FileResponse file = fileService.getFileById(id);
    return ResponseEntity.ok(ApiResponse.success(file));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<FileResponse>>> getAll() {
    List<FileResponse> files = fileService.getAllFiles();
    return ResponseEntity.ok(ApiResponse.success(files));
  }
}
