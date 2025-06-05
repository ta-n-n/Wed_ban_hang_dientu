package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.ProductVariantResponse;
import org.soft.elec.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-variants")
public class ProductVariantController {

  @Autowired private ProductVariantService productVariantService;

  @PostMapping
  public ApiResponse<ProductVariantResponse> create(
      @RequestBody @Valid ProductVariantRequest request) {
    return ApiResponse.<ProductVariantResponse>builder()
        .success(true)
        .data(productVariantService.createProductVariant(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<ProductVariantResponse> update(
      @PathVariable Integer id, @RequestBody @Valid ProductVariantRequest request) {
    return ApiResponse.<ProductVariantResponse>builder()
        .success(true)
        .data(productVariantService.updateProductVariant(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    productVariantService.deleteProductVariant(id);
    return ApiResponse.<String>builder()
        .success(true)
        .data("Product variant has been deleted")
        .build();
  }

  @GetMapping("/{id}")
  public ApiResponse<ProductVariantResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<ProductVariantResponse>builder()
        .success(true)
        .data(productVariantService.getProductVariantById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<ProductVariantResponse>> getAll() {
    return ApiResponse.<List<ProductVariantResponse>>builder()
        .success(true)
        .data(productVariantService.getAllProductVariants())
        .build();
  }
}
