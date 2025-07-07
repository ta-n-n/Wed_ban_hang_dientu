package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OptionValueRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OptionValueResponse;
import org.soft.elec.service.OptionValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/option-values")
@RequiredArgsConstructor
public class OptionValueController {

  private final OptionValueService optionValueService;

  @PostMapping
  public ResponseEntity<ApiResponse<OptionValueResponse>> create(
      @Valid @RequestBody OptionValueRequest request) {
    OptionValueResponse created = optionValueService.createOptionValue(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<OptionValueResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody OptionValueRequest request) {
    OptionValueResponse updated = optionValueService.updateOptionValue(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    optionValueService.deleteOptionValue(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<OptionValueResponse>> getById(@PathVariable Integer id) {
    OptionValueResponse value = optionValueService.getOptionValueById(id);
    return ResponseEntity.ok(ApiResponse.success(value));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<OptionValueResponse>>> getAll() {
    List<OptionValueResponse> values = optionValueService.getAllOptionValues();
    return ResponseEntity.ok(ApiResponse.success(values));
  }
}
