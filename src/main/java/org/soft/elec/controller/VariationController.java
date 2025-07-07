package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.VariationRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.VariationResponse;
import org.soft.elec.service.VariationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/variations")
@RequiredArgsConstructor
public class VariationController {

  private final VariationService variationService;

  @PostMapping
  public ResponseEntity<ApiResponse<VariationResponse>> create(
      @Valid @RequestBody VariationRequest request) {
    VariationResponse created = variationService.createVariation(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<VariationResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody VariationRequest request) {
    VariationResponse updated = variationService.updateVariation(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    variationService.deleteVariation(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<VariationResponse>> getById(@PathVariable Integer id) {
    VariationResponse variation = variationService.getVariationById(id);
    return ResponseEntity.ok(ApiResponse.success(variation));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<VariationResponse>>> getAll() {
    List<VariationResponse> variations = variationService.getAllVariations();
    return ResponseEntity.ok(ApiResponse.success(variations));
  }
}
