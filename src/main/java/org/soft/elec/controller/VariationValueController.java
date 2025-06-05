package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.VariationValueRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.VariationValueResponse;
import org.soft.elec.service.VariationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/variation-values")
public class VariationValueController {

  @Autowired private VariationValueService variationValueService;

  @PostMapping
  public ApiResponse<VariationValueResponse> create(
      @RequestBody @Valid VariationValueRequest request) {
    return ApiResponse.<VariationValueResponse>builder()
        .success(true)
        .data(variationValueService.createVariationValue(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<VariationValueResponse> update(
      @PathVariable Integer id, @RequestBody @Valid VariationValueRequest request) {
    return ApiResponse.<VariationValueResponse>builder()
        .success(true)
        .data(variationValueService.updateVariationValue(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    variationValueService.deleteVariationValue(id);
    return ApiResponse.<String>builder()
        .success(true)
        .data("Variation Value has been deleted")
        .build();
  }

  @GetMapping("/{id}")
  public ApiResponse<VariationValueResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<VariationValueResponse>builder()
        .success(true)
        .data(variationValueService.getVariationValueById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<VariationValueResponse>> getAll() {
    return ApiResponse.<List<VariationValueResponse>>builder()
        .success(true)
        .data(variationValueService.getAllVariationValues())
        .build();
  }
}
