package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.OrderRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OrderResponse;
import org.soft.elec.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

  @Autowired private OrderService orderService;

  @PostMapping
  public ApiResponse<OrderResponse> create(@RequestBody @Valid OrderRequest request) {
    return ApiResponse.<OrderResponse>builder()
        .success(true)
        .data(orderService.createOrder(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<OrderResponse> update(
      @PathVariable Integer id, @RequestBody @Valid OrderRequest request) {
    return ApiResponse.<OrderResponse>builder()
        .success(true)
        .data(orderService.updateOrder(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    orderService.deleteOrder(id);
    return ApiResponse.<String>builder().success(true).data("Order has been deleted").build();
  }

  @GetMapping("/{id}")
  public ApiResponse<OrderResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<OrderResponse>builder()
        .success(true)
        .data(orderService.getOrderById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<OrderResponse>> getAll() {
    return ApiResponse.<List<OrderResponse>>builder()
        .success(true)
        .data(orderService.getAllOrders())
        .build();
  }
}
