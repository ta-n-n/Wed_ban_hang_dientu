package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.ProductVariantResponse;
import org.soft.elec.service.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

  private final ProductVariantService productVariantService;

  @PostMapping
  public ResponseEntity<ApiResponse<ProductVariantResponse>> create(
      @Valid @RequestBody ProductVariantRequest request) {
    ProductVariantResponse created = productVariantService.createProductVariant(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductVariantResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody ProductVariantRequest request) {
    ProductVariantResponse updated = productVariantService.updateProductVariant(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    productVariantService.deleteProductVariant(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductVariantResponse>> getById(@PathVariable Integer id) {
    ProductVariantResponse result = productVariantService.getProductVariantById(id);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<ProductVariantResponse>>> getAll() {
    List<ProductVariantResponse> results = productVariantService.getAllProductVariants();
    return ResponseEntity.ok(ApiResponse.success(results));
  }
}
