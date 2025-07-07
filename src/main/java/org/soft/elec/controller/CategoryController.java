package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.CategoryResponse;
import org.soft.elec.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<ApiResponse<CategoryResponse>> create(
      @Valid @RequestBody CategoryRequest request) {
    CategoryResponse created = categoryService.createCategory(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<CategoryResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody CategoryRequest request) {
    CategoryResponse updated = categoryService.updateCategory(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Integer id) {
    CategoryResponse category = categoryService.getCategoryById(id);
    return ResponseEntity.ok(ApiResponse.success(category));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll() {
    List<CategoryResponse> categories = categoryService.getAllCategories();
    return ResponseEntity.ok(ApiResponse.success(categories));
  }
}
