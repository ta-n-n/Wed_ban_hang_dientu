package org.soft.elec.service;

import org.soft.elec.entity.dto.request.OrderProductVariationValueRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationValueResponse;
import java.util.List;

public interface OrderProductVariationValueService {
    OrderProductVariationValueResponse createOrderProductVariationValue(OrderProductVariationValueRequest request);
    OrderProductVariationValueResponse updateOrderProductVariationValue(Integer id, OrderProductVariationValueRequest request);
    void deleteOrderProductVariationValue(Integer id);
    OrderProductVariationValueResponse getOrderProductVariationValueById(Integer id);
    List<OrderProductVariationValueResponse> getAllOrderProductVariationValues();
}
