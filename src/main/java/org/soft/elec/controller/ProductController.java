package org.soft.elec.controller;

import jakarta.validation.Valid;
import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.ProductResponse;
import org.soft.elec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> create(
            @RequestBody @Valid ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .success(true)
                .data(productService.createProduct(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(
            @PathVariable Integer id,
            @RequestBody @Valid ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .success(true)
                .data(productService.updateProduct(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(
            @PathVariable Integer id) {
        productService.deleteProduct(id);
        return ApiResponse.<String>builder()
                .success(true)
                .data("Product has been deleted")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getById(
            @PathVariable Integer id) {
        return ApiResponse.<ProductResponse>builder()
                .success(true)
                .data(productService.getProductById(id))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAll() {
        return ApiResponse.<List<ProductResponse>>builder()
                .success(true)
                .data(productService.getAllProducts())
                .build();
    }
}
