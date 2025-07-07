package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.ProductResponse;
import org.soft.elec.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ApiResponse<ProductResponse>> create(
      @Valid @RequestBody ProductRequest request) {
    ProductResponse created = productService.createProduct(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody ProductRequest request) {
    ProductResponse updated = productService.updateProduct(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    productService.deleteProduct(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Integer id) {
    ProductResponse result = productService.getProductById(id);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll() {
    List<ProductResponse> products = productService.getAllProducts();
    return ResponseEntity.ok(ApiResponse.success(products));
  }
}
