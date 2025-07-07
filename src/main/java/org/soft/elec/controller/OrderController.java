package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OrderRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OrderResponse;
import org.soft.elec.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<ApiResponse<OrderResponse>> create(
      @Valid @RequestBody OrderRequest request) {
    OrderResponse created = orderService.createOrder(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody OrderRequest request) {
    OrderResponse updated = orderService.updateOrder(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    orderService.deleteOrder(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderResponse>> getById(@PathVariable Integer id) {
    OrderResponse order = orderService.getOrderById(id);
    return ResponseEntity.ok(ApiResponse.success(order));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<OrderResponse>>> getAll() {
    List<OrderResponse> orders = orderService.getAllOrders();
    return ResponseEntity.ok(ApiResponse.success(orders));
  }
}
