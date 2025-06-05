package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.OrderProductVariationValueRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationValueResponse;

public interface OrderProductVariationValueService {
  OrderProductVariationValueResponse createOrderProductVariationValue(
      OrderProductVariationValueRequest request);

  OrderProductVariationValueResponse updateOrderProductVariationValue(
      Integer id, OrderProductVariationValueRequest request);

  void deleteOrderProductVariationValue(Integer id);

  OrderProductVariationValueResponse getOrderProductVariationValueById(Integer id);

  List<OrderProductVariationValueResponse> getAllOrderProductVariationValues();
}
