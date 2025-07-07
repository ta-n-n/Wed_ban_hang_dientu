package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.EntityFileRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.EntityFileResponse;
import org.soft.elec.service.EntityFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/entity-files")
@RequiredArgsConstructor
public class EntityFileController {

  private final EntityFileService entityFileService;

  @PostMapping
  public ResponseEntity<ApiResponse<EntityFileResponse>> create(
      @Valid @RequestBody EntityFileRequest request) {
    EntityFileResponse created = entityFileService.createEntityFile(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<EntityFileResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody EntityFileRequest request) {
    EntityFileResponse updated = entityFileService.updateEntityFile(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    entityFileService.deleteEntityFile(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<EntityFileResponse>> getById(@PathVariable Integer id) {
    EntityFileResponse entityFile = entityFileService.getEntityFileById(id);
    return ResponseEntity.ok(ApiResponse.success(entityFile));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EntityFileResponse>>> getAll() {
    List<EntityFileResponse> entityFiles = entityFileService.getAllEntityFiles();
    return ResponseEntity.ok(ApiResponse.success(entityFiles));
  }
}
