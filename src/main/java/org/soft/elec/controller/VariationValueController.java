package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.VariationValueRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.VariationValueResponse;
import org.soft.elec.service.VariationValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/variation-values")
@RequiredArgsConstructor
public class VariationValueController {

  private final VariationValueService variationValueService;

  @PostMapping
  public ResponseEntity<ApiResponse<VariationValueResponse>> create(
      @Valid @RequestBody VariationValueRequest request) {
    VariationValueResponse created = variationValueService.createVariationValue(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<VariationValueResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody VariationValueRequest request) {
    VariationValueResponse updated = variationValueService.updateVariationValue(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    variationValueService.deleteVariationValue(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<VariationValueResponse>> getById(@PathVariable Integer id) {
    VariationValueResponse response = variationValueService.getVariationValueById(id);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<VariationValueResponse>>> getAll() {
    List<VariationValueResponse> list = variationValueService.getAllVariationValues();
    return ResponseEntity.ok(ApiResponse.success(list));
  }
}
