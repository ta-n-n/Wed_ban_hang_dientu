package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.OrderRequest;
import org.soft.elec.entity.dto.response.OrderResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.OrderMapper;
import org.soft.elec.entity.models.Order;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.OrderRepository;
import org.soft.elec.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired private OrderRepository orderRepository;

  @Autowired private OrderMapper orderMapper;

  private void checkOrderExist(Integer id) {
    if (!orderRepository.existsById(id)) {
      throw new AppEx(ErrorCode.ORDER_NOT_FOUND);
    }
  }

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
        orderRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.ORDER_NOT_FOUND));
    orderMapper.updateEntity(request, order);
    Order updated = orderRepository.save(order);
    return orderMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOrder(Integer id) {
    checkOrderExist(id);
    orderRepository.deleteById(id);
  }

  @Override
  public OrderResponse getOrderById(Integer id) {
    Order order =
        orderRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.ORDER_NOT_FOUND));
    return orderMapper.toResponse(order);
  }

  @Override
  public List<OrderResponse> getAllOrders() {
    return orderRepository.findAll().stream()
        .map(orderMapper::toResponse)
        .collect(Collectors.toList());
  }
}
