package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OrderProductVariationValueRequest;
import org.soft.elec.entity.dto.request.search.OrderProductVariationValueSearchRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationValueResponse;
import org.soft.elec.entity.mapper.OrderProductVariationValueMapper;
import org.soft.elec.entity.models.OrderProductVariationValue;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.OrderProductVariationValueRepository;
import org.soft.elec.service.OrderProductVariationValueService;
import org.soft.elec.specification.OrderProductVariationValueSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderProductVariationValueServiceImpl implements OrderProductVariationValueService {

  private final OrderProductVariationValueRepository orderProductVariationValueRepository;
  private final OrderProductVariationValueMapper orderProductVariationValueMapper;

  @Override
  @Transactional
  public OrderProductVariationValueResponse createOrderProductVariationValue(
      OrderProductVariationValueRequest request) {
    OrderProductVariationValue entity = orderProductVariationValueMapper.toEntity(request);
    OrderProductVariationValue saved = orderProductVariationValueRepository.save(entity);
    return orderProductVariationValueMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public OrderProductVariationValueResponse updateOrderProductVariationValue(
      Integer id, OrderProductVariationValueRequest request) {
    OrderProductVariationValue existing =
        orderProductVariationValueRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_VARIATION_VALUE_NOT_FOUND));

    orderProductVariationValueMapper.updateEntity(request, existing);
    OrderProductVariationValue updated = orderProductVariationValueRepository.save(existing);
    return orderProductVariationValueMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOrderProductVariationValue(Integer id) {
    OrderProductVariationValue existing =
        orderProductVariationValueRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_VARIATION_VALUE_NOT_FOUND));
    orderProductVariationValueRepository.delete(existing);
  }

  @Override
  public OrderProductVariationValueResponse getOrderProductVariationValueById(Integer id) {
    OrderProductVariationValue entity =
        orderProductVariationValueRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_VARIATION_VALUE_NOT_FOUND));
    return orderProductVariationValueMapper.toResponse(entity);
  }

  @Override
  public List<OrderProductVariationValueResponse> getAllOrderProductVariationValues() {
    return orderProductVariationValueRepository.findAll().stream()
        .map(orderProductVariationValueMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<OrderProductVariationValueResponse> searchOrderProductVariationValues(
      OrderProductVariationValueSearchRequest request) {
    Specification<OrderProductVariationValue> spec = null;
    spec =
        SpecUtil.add(
            spec,
            OrderProductVariationValueSpecification.hasOrderProductVariationId(
                request.getOrderProductVariationId()));
    spec =
        SpecUtil.add(
            spec,
            OrderProductVariationValueSpecification.hasVariationValueId(
                request.getVariationValueId()));

    Pageable pageable = PageUtil.getPageable(request);

    return orderProductVariationValueRepository
        .findAll(spec, pageable)
        .map(orderProductVariationValueMapper::toResponse);
  }
}
