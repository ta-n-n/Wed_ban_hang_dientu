package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.OrderProductVariationRequest;
import org.soft.elec.entity.dto.request.search.OrderProductVariationSearchRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationResponse;
import org.springframework.data.domain.Page;

public interface OrderProductVariationService {
  OrderProductVariationResponse createOrderProductVariation(OrderProductVariationRequest request);

  OrderProductVariationResponse updateOrderProductVariation(
      Integer id, OrderProductVariationRequest request);

  void deleteOrderProductVariation(Integer id);

  OrderProductVariationResponse getOrderProductVariationById(Integer id);

  List<OrderProductVariationResponse> getAllOrderProductVariations();

  Page<OrderProductVariationResponse> searchOrderProductVariations(
      OrderProductVariationSearchRequest request);
}
