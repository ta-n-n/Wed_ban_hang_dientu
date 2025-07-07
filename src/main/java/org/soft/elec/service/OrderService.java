package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.OrderRequest;
import org.soft.elec.entity.dto.request.search.OrderSearchRequest;
import org.soft.elec.entity.dto.response.OrderResponse;
import org.springframework.data.domain.Page;

public interface OrderService {
  OrderResponse createOrder(OrderRequest request);

  OrderResponse updateOrder(Integer id, OrderRequest request);

  void deleteOrder(Integer id);

  OrderResponse getOrderById(Integer id);

  List<OrderResponse> getAllOrders();

  Page<OrderResponse> searchOrders(OrderSearchRequest request);
}
