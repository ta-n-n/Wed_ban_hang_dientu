package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.CategoryResponse;
import org.soft.elec.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  @Autowired private CategoryService categoryService;

  @PostMapping
  public ApiResponse<CategoryResponse> create(@RequestBody @Valid CategoryRequest request) {
    return ApiResponse.<CategoryResponse>builder()
        .success(true)
        .data(categoryService.createCategory(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<CategoryResponse> update(
      @PathVariable Integer id, @RequestBody @Valid CategoryRequest request) {
    return ApiResponse.<CategoryResponse>builder()
        .success(true)
        .data(categoryService.updateCategory(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    categoryService.deleteCategory(id);
    return ApiResponse.<String>builder().success(true).data("Category has been deleted").build();
  }

  @GetMapping("/{id}")
  public ApiResponse<CategoryResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<CategoryResponse>builder()
        .success(true)
        .data(categoryService.getCategoryById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<CategoryResponse>> getAll() {
    return ApiResponse.<List<CategoryResponse>>builder()
        .success(true)
        .data(categoryService.getAllCategories())
        .build();
  }
}
