package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.FileRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.FileResponse;
import org.soft.elec.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

  @Autowired private FileService fileService;

  @PostMapping
  public ApiResponse<FileResponse> create(@RequestBody @Valid FileRequest request) {
    return ApiResponse.<FileResponse>builder()
        .success(true)
        .data(fileService.createFile(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<FileResponse> update(
      @PathVariable Integer id, @RequestBody @Valid FileRequest request) {
    return ApiResponse.<FileResponse>builder()
        .success(true)
        .data(fileService.updateFile(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    fileService.deleteFile(id);
    return ApiResponse.<String>builder().success(true).data("File has been deleted").build();
  }

  @GetMapping("/{id}")
  public ApiResponse<FileResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<FileResponse>builder()
        .success(true)
        .data(fileService.getFileById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<FileResponse>> getAll() {
    return ApiResponse.<List<FileResponse>>builder()
        .success(true)
        .data(fileService.getAllFiles())
        .build();
  }
}
