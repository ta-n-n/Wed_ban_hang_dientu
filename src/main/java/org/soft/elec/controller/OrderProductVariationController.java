package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.OrderProductVariationRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OrderProductVariationResponse;
import org.soft.elec.service.OrderProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-product-variations")
public class OrderProductVariationController {

  @Autowired private OrderProductVariationService orderProductVariationService;

  @PostMapping
  public ApiResponse<OrderProductVariationResponse> create(
      @RequestBody @Valid OrderProductVariationRequest request) {
    return ApiResponse.<OrderProductVariationResponse>builder()
        .success(true)
        .data(orderProductVariationService.createOrderProductVariation(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<OrderProductVariationResponse> update(
      @PathVariable Integer id, @RequestBody @Valid OrderProductVariationRequest request) {
    return ApiResponse.<OrderProductVariationResponse>builder()
        .success(true)
        .data(orderProductVariationService.updateOrderProductVariation(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    orderProductVariationService.deleteOrderProductVariation(id);
    return ApiResponse.<String>builder()
        .success(true)
        .data("Order Product Variation has been deleted")
        .build();
  }

  @GetMapping("/{id}")
  public ApiResponse<OrderProductVariationResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<OrderProductVariationResponse>builder()
        .success(true)
        .data(orderProductVariationService.getOrderProductVariationById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<OrderProductVariationResponse>> getAll() {
    return ApiResponse.<List<OrderProductVariationResponse>>builder()
        .success(true)
        .data(orderProductVariationService.getAllOrderProductVariations())
        .build();
  }
}
