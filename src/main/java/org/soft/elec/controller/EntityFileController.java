package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.EntityFileRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.EntityFileResponse;
import org.soft.elec.service.EntityFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/entity-files")
public class EntityFileController {

  @Autowired private EntityFileService entityFileService;

  @PostMapping
  public ApiResponse<EntityFileResponse> create(@RequestBody @Valid EntityFileRequest request) {
    return ApiResponse.<EntityFileResponse>builder()
        .success(true)
        .data(entityFileService.createEntityFile(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<EntityFileResponse> update(
      @PathVariable Integer id, @RequestBody @Valid EntityFileRequest request) {
    return ApiResponse.<EntityFileResponse>builder()
        .success(true)
        .data(entityFileService.updateEntityFile(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    entityFileService.deleteEntityFile(id);
    return ApiResponse.<String>builder().success(true).data("Entity file has been deleted").build();
  }

  @GetMapping("/{id}")
  public ApiResponse<EntityFileResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<EntityFileResponse>builder()
        .success(true)
        .data(entityFileService.getEntityFileById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<EntityFileResponse>> getAll() {
    return ApiResponse.<List<EntityFileResponse>>builder()
        .success(true)
        .data(entityFileService.getAllEntityFiles())
        .build();
  }
}
