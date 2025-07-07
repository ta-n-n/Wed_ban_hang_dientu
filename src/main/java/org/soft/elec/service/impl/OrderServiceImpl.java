package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OrderRequest;
import org.soft.elec.entity.dto.request.search.OrderSearchRequest;
import org.soft.elec.entity.dto.response.OrderResponse;
import org.soft.elec.entity.mapper.OrderMapper;
import org.soft.elec.entity.models.Order;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.OrderRepository;
import org.soft.elec.service.OrderService;
import org.soft.elec.specification.OrderSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;

  @Override
  @Transactional
  public OrderResponse createOrder(OrderRequest request) {
    Order order = orderMapper.toEntity(request);
    Order saved = orderRepository.save(order);
    return orderMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public OrderResponse updateOrder(Integer id, OrderRequest request) {
    Order order =
        orderRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    orderMapper.updateEntity(request, order);
    Order updated = orderRepository.save(order);
    return orderMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOrder(Integer id) {
    Order existing =
        orderRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    orderRepository.delete(existing);
  }

  @Override
  public OrderResponse getOrderById(Integer id) {
    Order order =
        orderRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    return orderMapper.toResponse(order);
  }

  @Override
  public List<OrderResponse> getAllOrders() {
    return orderRepository.findAll().stream()
        .map(orderMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<OrderResponse> searchOrders(OrderSearchRequest request) {
    Specification<Order> spec = null;
    spec = SpecUtil.add(spec, OrderSpecification.hasCustomerEmail(request.getCustomerEmail()));
    spec = SpecUtil.add(spec, OrderSpecification.hasCustomerPhone(request.getCustomerPhone()));
    spec = SpecUtil.add(spec, OrderSpecification.hasStatus(request.getStatus()));
    spec = SpecUtil.add(spec, OrderSpecification.createdAfter(request.getFromCreatedAt()));
    spec = SpecUtil.add(spec, OrderSpecification.createdBefore(request.getToCreatedAt()));
    spec = SpecUtil.add(spec, OrderSpecification.totalGreaterThan(request.getMinTotal()));
    spec = SpecUtil.add(spec, OrderSpecification.totalLessThan(request.getMaxTotal()));
    Pageable pageable = PageUtil.getPageable(request);
    return orderRepository.findAll(spec, pageable).map(orderMapper::toResponse);
  }
}
