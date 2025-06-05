package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.OrderProductVariationValueRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.OrderProductVariationValueResponse;
import org.soft.elec.service.OrderProductVariationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-product-variation-values")
public class OrderProductVariationValueController {

  @Autowired private OrderProductVariationValueService orderProductVariationValueService;

  @PostMapping
  public ApiResponse<OrderProductVariationValueResponse> create(
      @RequestBody @Valid OrderProductVariationValueRequest request) {
    return ApiResponse.<OrderProductVariationValueResponse>builder()
        .success(true)
        .data(orderProductVariationValueService.createOrderProductVariationValue(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<OrderProductVariationValueResponse> update(
      @PathVariable Integer id, @RequestBody @Valid OrderProductVariationValueRequest request) {
    return ApiResponse.<OrderProductVariationValueResponse>builder()
        .success(true)
        .data(orderProductVariationValueService.updateOrderProductVariationValue(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    orderProductVariationValueService.deleteOrderProductVariationValue(id);
    return ApiResponse.<String>builder()
        .success(true)
        .data("Order Product Variation Value has been deleted")
        .build();
  }

  @GetMapping("/{id}")
  public ApiResponse<OrderProductVariationValueResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<OrderProductVariationValueResponse>builder()
        .success(true)
        .data(orderProductVariationValueService.getOrderProductVariationValueById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<OrderProductVariationValueResponse>> getAll() {
    return ApiResponse.<List<OrderProductVariationValueResponse>>builder()
        .success(true)
        .data(orderProductVariationValueService.getAllOrderProductVariationValues())
        .build();
  }
}
