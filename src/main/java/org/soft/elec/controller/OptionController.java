package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OptionRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OptionResponse;
import org.soft.elec.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/options")
@RequiredArgsConstructor
public class OptionController {

  private final OptionService optionService;

  @PostMapping
  public ResponseEntity<ApiResponse<OptionResponse>> create(
      @Valid @RequestBody OptionRequest request) {
    OptionResponse created = optionService.createOption(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<OptionResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody OptionRequest request) {
    OptionResponse updated = optionService.updateOption(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    optionService.deleteOption(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<OptionResponse>> getById(@PathVariable Integer id) {
    OptionResponse option = optionService.getOptionById(id);
    return ResponseEntity.ok(ApiResponse.success(option));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<OptionResponse>>> getAll() {
    List<OptionResponse> options = optionService.getAllOptions();
    return ResponseEntity.ok(ApiResponse.success(options));
  }
}
