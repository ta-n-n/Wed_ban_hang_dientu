package org.soft.elec.service;

import org.soft.elec.entity.dto.request.OrderProductVariationRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationResponse;
import java.util.List;

public interface OrderProductVariationService {
    OrderProductVariationResponse createOrderProductVariation(OrderProductVariationRequest request);
    OrderProductVariationResponse updateOrderProductVariation(Integer id, OrderProductVariationRequest request);
    void deleteOrderProductVariation(Integer id);
    OrderProductVariationResponse getOrderProductVariationById(Integer id);
    List<OrderProductVariationResponse> getAllOrderProductVariations();
}
