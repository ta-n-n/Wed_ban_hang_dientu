package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OrderProductRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OrderProductResponse;
import org.soft.elec.service.OrderProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-products")
@RequiredArgsConstructor
public class OrderProductController {

  private final OrderProductService orderProductService;

  @PostMapping
  public ResponseEntity<ApiResponse<OrderProductResponse>> create(
      @Valid @RequestBody OrderProductRequest request) {
    OrderProductResponse created = orderProductService.createOrderProduct(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderProductResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody OrderProductRequest request) {
    OrderProductResponse updated = orderProductService.updateOrderProduct(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    orderProductService.deleteOrderProduct(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderProductResponse>> getById(@PathVariable Integer id) {
    OrderProductResponse orderProduct = orderProductService.getOrderProductById(id);
    return ResponseEntity.ok(ApiResponse.success(orderProduct));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<OrderProductResponse>>> getAll() {
    List<OrderProductResponse> list = orderProductService.getAllOrderProducts();
    return ResponseEntity.ok(ApiResponse.success(list));
  }
}
