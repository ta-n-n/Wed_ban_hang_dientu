package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.OrderProductRequest;
import org.soft.elec.entity.dto.response.OrderProductResponse;

public interface OrderProductService {
  OrderProductResponse createOrderProduct(OrderProductRequest request);

  OrderProductResponse updateOrderProduct(Integer id, OrderProductRequest request);

  void deleteOrderProduct(Integer id);

  OrderProductResponse getOrderProductById(Integer id);

  List<OrderProductResponse> getAllOrderProducts();
}
