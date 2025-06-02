package org.soft.elec.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.BrandRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.BrandResponse;
import org.soft.elec.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ApiResponse<BrandResponse> create(
            @RequestBody @Valid BrandRequest request) {
        return ApiResponse.<BrandResponse>builder()
                .success(true)
                .data(brandService.createBrand(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BrandResponse> update(
            @PathVariable Integer id,
            @RequestBody @Valid BrandRequest request) {
        return ApiResponse.<BrandResponse>builder()
                .success(true)
                .data(brandService.updateBrand(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(
            @PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ApiResponse.<String>builder()
                .success(true)
                .data("brand has been deleted")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BrandResponse> getById(
            @PathVariable Integer id) {
        return ApiResponse.<BrandResponse>builder()
                .success(true)
                .data(brandService.getBrandById(id))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BrandResponse>> getAll() {
        return ApiResponse.<List<BrandResponse>>builder()
                .success(true)
                .data(brandService.getAllBrands())
                .build();
    }
}
