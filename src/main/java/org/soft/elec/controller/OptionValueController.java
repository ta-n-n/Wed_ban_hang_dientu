package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.OptionValueRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OptionValueResponse;
import org.soft.elec.service.OptionValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/option-values")
public class OptionValueController {

  @Autowired private OptionValueService optionValueService;

  @PostMapping
  public ApiResponse<OptionValueResponse> create(@RequestBody @Valid OptionValueRequest request) {
    return ApiResponse.<OptionValueResponse>builder()
        .success(true)
        .data(optionValueService.createOptionValue(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<OptionValueResponse> update(
      @PathVariable Integer id, @RequestBody @Valid OptionValueRequest request) {
    return ApiResponse.<OptionValueResponse>builder()
        .success(true)
        .data(optionValueService.updateOptionValue(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    optionValueService.deleteOptionValue(id);
    return ApiResponse.<String>builder()
        .success(true)
        .data("Option Value has been deleted")
        .build();
  }

  @GetMapping("/{id}")
  public ApiResponse<OptionValueResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<OptionValueResponse>builder()
        .success(true)
        .data(optionValueService.getOptionValueById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<OptionValueResponse>> getAll() {
    return ApiResponse.<List<OptionValueResponse>>builder()
        .success(true)
        .data(optionValueService.getAllOptionValues())
        .build();
  }
}
