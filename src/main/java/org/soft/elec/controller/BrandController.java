package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.BrandRequest;
import org.soft.elec.entity.dto.request.search.BrandSearchRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.BrandResponse;
import org.soft.elec.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

  private final BrandService brandService;

  @PostMapping
  public ResponseEntity<ApiResponse<BrandResponse>> create(
      @Valid @RequestBody BrandRequest request) {
    BrandResponse created = brandService.createBrand(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<BrandResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody BrandRequest request) {
    BrandResponse updated = brandService.updateBrand(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    brandService.deleteBrand(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<BrandResponse>> getById(@PathVariable Integer id) {
    BrandResponse brand = brandService.getBrandById(id);
    return ResponseEntity.ok(ApiResponse.success(brand));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<BrandResponse>>> getAll() {
    List<BrandResponse> brands = brandService.getAllBrands();
    return ResponseEntity.ok(ApiResponse.success(brands));
  }

  @GetMapping("/search")
  public ResponseEntity<ApiResponse<Page<BrandResponse>>> searchBrands(BrandSearchRequest request) {
    Page<BrandResponse> result = brandService.searchBrands(request);
    return ResponseEntity.ok(ApiResponse.success(result));
  }
}
