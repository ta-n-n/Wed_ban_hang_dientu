package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.OrderProductRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OrderProductResponse;
import org.soft.elec.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-products")
public class OrderProductController {

  @Autowired private OrderProductService orderProductService;

  @PostMapping
  public ApiResponse<OrderProductResponse> create(@RequestBody @Valid OrderProductRequest request) {
    return ApiResponse.<OrderProductResponse>builder()
        .success(true)
        .data(orderProductService.createOrderProduct(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<OrderProductResponse> update(
      @PathVariable Integer id, @RequestBody @Valid OrderProductRequest request) {
    return ApiResponse.<OrderProductResponse>builder()
        .success(true)
        .data(orderProductService.updateOrderProduct(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    orderProductService.deleteOrderProduct(id);
    return ApiResponse.<String>builder()
        .success(true)
        .data("Order product has been deleted")
        .build();
  }

  @GetMapping("/{id}")
  public ApiResponse<OrderProductResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<OrderProductResponse>builder()
        .success(true)
        .data(orderProductService.getOrderProductById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<OrderProductResponse>> getAll() {
    return ApiResponse.<List<OrderProductResponse>>builder()
        .success(true)
        .data(orderProductService.getAllOrderProducts())
        .build();
  }
}
