package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.OrderProductVariationValueRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationValueResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.OrderProductVariationValueMapper;
import org.soft.elec.entity.models.OrderProductVariationValue;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.OrderProductVariationValueRepository;
import org.soft.elec.service.OrderProductVariationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProductVariationValueServiceImpl implements OrderProductVariationValueService {

  @Autowired private OrderProductVariationValueRepository orderProductVariationValueRepository;

  @Autowired private OrderProductVariationValueMapper orderProductVariationValueMapper;

  private void checkOrderProductVariationValueExist(Integer id) {
    if (!orderProductVariationValueRepository.existsById(id)) {
      throw new AppEx(ErrorCode.ORDER_PRODUCT_VARIATION_VALUE_ALREADY_EXISTS);
    }
  }

  @Override
  @Transactional
  public OrderProductVariationValueResponse createOrderProductVariationValue(
      OrderProductVariationValueRequest request) {
    OrderProductVariationValue orderProductVariationValue =
        orderProductVariationValueMapper.toEntity(request);
    OrderProductVariationValue saved =
        orderProductVariationValueRepository.save(orderProductVariationValue);
    return orderProductVariationValueMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public OrderProductVariationValueResponse updateOrderProductVariationValue(
      Integer id, OrderProductVariationValueRequest request) {
    OrderProductVariationValue orderProductVariationValue =
        orderProductVariationValueRepository
            .findById(id)
            .orElseThrow(() -> new AppEx(ErrorCode.ORDER_PRODUCT_VARIATION_VALUE_NOT_FOUND));
    orderProductVariationValueMapper.updateEntity(request, orderProductVariationValue);
    OrderProductVariationValue updated =
        orderProductVariationValueRepository.save(orderProductVariationValue);
    return orderProductVariationValueMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOrderProductVariationValue(Integer id) {
    checkOrderProductVariationValueExist(id);
    orderProductVariationValueRepository.deleteById(id);
  }

  @Override
  public OrderProductVariationValueResponse getOrderProductVariationValueById(Integer id) {
    OrderProductVariationValue orderProductVariationValue =
        orderProductVariationValueRepository
            .findById(id)
            .orElseThrow(() -> new AppEx(ErrorCode.ORDER_PRODUCT_VARIATION_VALUE_NOT_FOUND));
    return orderProductVariationValueMapper.toResponse(orderProductVariationValue);
  }

  @Override
  public List<OrderProductVariationValueResponse> getAllOrderProductVariationValues() {
    return orderProductVariationValueRepository.findAll().stream()
        .map(orderProductVariationValueMapper::toResponse)
        .collect(Collectors.toList());
  }
}
