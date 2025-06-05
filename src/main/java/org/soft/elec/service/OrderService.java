package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.OrderRequest;
import org.soft.elec.entity.dto.response.OrderResponse;

public interface OrderService {
  OrderResponse createOrder(OrderRequest request);

  OrderResponse updateOrder(Integer id, OrderRequest request);

  void deleteOrder(Integer id);

  OrderResponse getOrderById(Integer id);

  List<OrderResponse> getAllOrders();
}
