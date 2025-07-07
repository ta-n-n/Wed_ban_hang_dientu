package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.OrderProductRequest;
import org.soft.elec.entity.dto.request.search.OrderProductSearchRequest;
import org.soft.elec.entity.dto.response.OrderProductResponse;
import org.springframework.data.domain.Page;

public interface OrderProductService {
  OrderProductResponse createOrderProduct(OrderProductRequest request);

  OrderProductResponse updateOrderProduct(Integer id, OrderProductRequest request);

  void deleteOrderProduct(Integer id);

  OrderProductResponse getOrderProductById(Integer id);

  List<OrderProductResponse> getAllOrderProducts();

  Page<OrderProductResponse> searchOrderProducts(OrderProductSearchRequest request);
}
