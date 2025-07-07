package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OrderProductVariationValueRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OrderProductVariationValueResponse;
import org.soft.elec.service.OrderProductVariationValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-product-variation-values")
@RequiredArgsConstructor
public class OrderProductVariationValueController {

  private final OrderProductVariationValueService orderProductVariationValueService;

  @PostMapping
  public ResponseEntity<ApiResponse<OrderProductVariationValueResponse>> create(
      @Valid @RequestBody OrderProductVariationValueRequest request) {
    OrderProductVariationValueResponse created =
        orderProductVariationValueService.createOrderProductVariationValue(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderProductVariationValueResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody OrderProductVariationValueRequest request) {
    OrderProductVariationValueResponse updated =
        orderProductVariationValueService.updateOrderProductVariationValue(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    orderProductVariationValueService.deleteOrderProductVariationValue(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderProductVariationValueResponse>> getById(
      @PathVariable Integer id) {
    OrderProductVariationValueResponse result =
        orderProductVariationValueService.getOrderProductVariationValueById(id);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<OrderProductVariationValueResponse>>> getAll() {
    List<OrderProductVariationValueResponse> list =
        orderProductVariationValueService.getAllOrderProductVariationValues();
    return ResponseEntity.ok(ApiResponse.success(list));
  }
}
