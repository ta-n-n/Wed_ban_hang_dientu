package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.OptionRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OptionResponse;
import org.soft.elec.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/options")
public class OptionController {

  @Autowired private OptionService optionService;

  @PostMapping
  public ApiResponse<OptionResponse> create(@RequestBody @Valid OptionRequest request) {
    return ApiResponse.<OptionResponse>builder()
        .success(true)
        .data(optionService.createOption(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<OptionResponse> update(
      @PathVariable Integer id, @RequestBody @Valid OptionRequest request) {
    return ApiResponse.<OptionResponse>builder()
        .success(true)
        .data(optionService.updateOption(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    optionService.deleteOption(id);
    return ApiResponse.<String>builder().success(true).data("Option has been deleted").build();
  }

  @GetMapping("/{id}")
  public ApiResponse<OptionResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<OptionResponse>builder()
        .success(true)
        .data(optionService.getOptionById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<OptionResponse>> getAll() {
    return ApiResponse.<List<OptionResponse>>builder()
        .success(true)
        .data(optionService.getAllOptions())
        .build();
  }
}
