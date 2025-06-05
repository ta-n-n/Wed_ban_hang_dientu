package org.soft.elec.service.impl;

import org.soft.elec.entity.dto.request.OrderProductRequest;
import org.soft.elec.entity.dto.response.OrderProductResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.OrderProductMapper;
import org.soft.elec.entity.models.OrderProduct;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.OrderProductRepository;
import org.soft.elec.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderProductMapper orderProductMapper;

    private void checkOrderProductExist(Integer id) {
        if (!orderProductRepository.existsById(id)) {
            throw new AppEx(ErrorCode.ORDER_PRODUCT_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional
    public OrderProductResponse createOrderProduct(OrderProductRequest request) {
        OrderProduct orderProduct = orderProductMapper.toEntity(request);
        OrderProduct saved = orderProductRepository.save(orderProduct);
        return orderProductMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OrderProductResponse updateOrderProduct(Integer id, OrderProductRequest request) {
        OrderProduct orderProduct = orderProductRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.ORDER_PRODUCT_NOT_FOUND));
        orderProductMapper.updateEntity(request, orderProduct);
        OrderProduct updated = orderProductRepository.save(orderProduct);
        return orderProductMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteOrderProduct(Integer id) {
        checkOrderProductExist(id);
        orderProductRepository.deleteById(id);
    }

    @Override
    public OrderProductResponse getOrderProductById(Integer id) {
        OrderProduct orderProduct = orderProductRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.ORDER_PRODUCT_NOT_FOUND));
        return orderProductMapper.toResponse(orderProduct);
    }

    @Override
    public List<OrderProductResponse> getAllOrderProducts() {
        return orderProductRepository.findAll()
                .stream()
                .map(orderProductMapper::toResponse)
                .collect(Collectors.toList());
    }
}
