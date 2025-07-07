package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OrderProductVariationRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OrderProductVariationResponse;
import org.soft.elec.service.OrderProductVariationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-product-variations")
@RequiredArgsConstructor
public class OrderProductVariationController {

  private final OrderProductVariationService orderProductVariationService;

  @PostMapping
  public ResponseEntity<ApiResponse<OrderProductVariationResponse>> create(
      @Valid @RequestBody OrderProductVariationRequest request) {
    OrderProductVariationResponse created =
        orderProductVariationService.createOrderProductVariation(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderProductVariationResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody OrderProductVariationRequest request) {
    OrderProductVariationResponse updated =
        orderProductVariationService.updateOrderProductVariation(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    orderProductVariationService.deleteOrderProductVariation(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderProductVariationResponse>> getById(
      @PathVariable Integer id) {
    OrderProductVariationResponse result =
        orderProductVariationService.getOrderProductVariationById(id);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<OrderProductVariationResponse>>> getAll() {
    List<OrderProductVariationResponse> list =
        orderProductVariationService.getAllOrderProductVariations();
    return ResponseEntity.ok(ApiResponse.success(list));
  }
}
