package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.VariationRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.VariationResponse;
import org.soft.elec.service.VariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/variations")
public class VariationController {

  @Autowired private VariationService variationService;

  @PostMapping
  public ApiResponse<VariationResponse> create(@RequestBody @Valid VariationRequest request) {
    return ApiResponse.<VariationResponse>builder()
        .success(true)
        .data(variationService.createVariation(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<VariationResponse> update(
      @PathVariable Integer id, @RequestBody @Valid VariationRequest request) {
    return ApiResponse.<VariationResponse>builder()
        .success(true)
        .data(variationService.updateVariation(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    variationService.deleteVariation(id);
    return ApiResponse.<String>builder().success(true).data("Variation has been deleted").build();
  }

  @GetMapping("/{id}")
  public ApiResponse<VariationResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<VariationResponse>builder()
        .success(true)
        .data(variationService.getVariationById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<VariationResponse>> getAll() {
    return ApiResponse.<List<VariationResponse>>builder()
        .success(true)
        .data(variationService.getAllVariations())
        .build();
  }
}
