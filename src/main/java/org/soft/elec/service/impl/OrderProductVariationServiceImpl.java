package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OrderProductVariationRequest;
import org.soft.elec.entity.dto.request.search.OrderProductVariationSearchRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationResponse;
import org.soft.elec.entity.mapper.OrderProductVariationMapper;
import org.soft.elec.entity.models.OrderProduct;
import org.soft.elec.entity.models.OrderProductVariation;
import org.soft.elec.entity.models.Variation;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.OrderProductRepository;
import org.soft.elec.repository.OrderProductVariationRepository;
import org.soft.elec.repository.VariationRepository;
import org.soft.elec.service.OrderProductVariationService;
import org.soft.elec.specification.OrderProductVariationSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderProductVariationServiceImpl implements OrderProductVariationService {

  private final OrderProductVariationRepository orderProductVariationRepository;
  private final OrderProductVariationMapper orderProductVariationMapper;
  private final OrderProductRepository orderProductRepository;
  private final VariationRepository variationRepository;

  @Override
  @Transactional
  public OrderProductVariationResponse createOrderProductVariation(
      OrderProductVariationRequest request) {
    OrderProduct orderProduct =
        orderProductRepository
            .findById(request.getOrderProductId())
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_NOT_FOUND));
    Variation variation =
        variationRepository
            .findById(request.getVariationId())
            .orElseThrow(() -> new AppException(ErrorCode.VARIATION_NOT_FOUND));
    OrderProductVariation entity = orderProductVariationMapper.toEntity(request);
    entity.setOrderProduct(orderProduct);
    entity.setVariation(variation);
    OrderProductVariation saved = orderProductVariationRepository.save(entity);
    return orderProductVariationMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public OrderProductVariationResponse updateOrderProductVariation(
      Integer id, OrderProductVariationRequest request) {

    OrderProductVariation existing =
        orderProductVariationRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_VARIATION_NOT_FOUND));
    if (request.getOrderProductId() != null) {
      OrderProduct orderProduct =
          orderProductRepository
              .findById(request.getOrderProductId())
              .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_NOT_FOUND));
      existing.setOrderProduct(orderProduct);
    }
    if (request.getVariationId() != null) {
      Variation variation =
          variationRepository
              .findById(request.getVariationId())
              .orElseThrow(() -> new AppException(ErrorCode.VARIATION_NOT_FOUND));
      existing.setVariation(variation);
    }

    orderProductVariationMapper.updateEntity(request, existing);
    OrderProductVariation updated = orderProductVariationRepository.save(existing);
    return orderProductVariationMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOrderProductVariation(Integer id) {
    OrderProductVariation existing =
        orderProductVariationRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_VARIATION_NOT_FOUND));
    orderProductVariationRepository.delete(existing);
  }

  @Override
  public OrderProductVariationResponse getOrderProductVariationById(Integer id) {
    OrderProductVariation entity =
        orderProductVariationRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_VARIATION_NOT_FOUND));
    return orderProductVariationMapper.toResponse(entity);
  }

  @Override
  public List<OrderProductVariationResponse> getAllOrderProductVariations() {
    return orderProductVariationRepository.findAll().stream()
        .map(orderProductVariationMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<OrderProductVariationResponse> searchOrderProductVariations(
      OrderProductVariationSearchRequest request) {
    Specification<OrderProductVariation> spec = null;

    spec =
        SpecUtil.add(
            spec,
            OrderProductVariationSpecification.hasOrderProductId(request.getOrderProductId()));
    spec =
        SpecUtil.add(
            spec, OrderProductVariationSpecification.hasVariationId(request.getVariationId()));
    spec = SpecUtil.add(spec, OrderProductVariationSpecification.hasType(request.getType()));
    spec = SpecUtil.add(spec, OrderProductVariationSpecification.hasValue(request.getValue()));

    Pageable pageable = PageUtil.getPageable(request);

    return orderProductVariationRepository
        .findAll(spec, pageable)
        .map(orderProductVariationMapper::toResponse);
  }
}
