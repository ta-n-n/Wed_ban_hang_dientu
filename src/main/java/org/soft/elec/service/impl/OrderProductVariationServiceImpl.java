package org.soft.elec.service.impl;

import org.soft.elec.entity.dto.request.OrderProductVariationRequest;
import org.soft.elec.entity.dto.response.OrderProductVariationResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.OrderProductVariationMapper;
import org.soft.elec.entity.models.OrderProductVariation;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.OrderProductVariationRepository;
import org.soft.elec.service.OrderProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProductVariationServiceImpl implements OrderProductVariationService {

    @Autowired
    private OrderProductVariationRepository orderProductVariationRepository;

    @Autowired
    private OrderProductVariationMapper orderProductVariationMapper;

    private void checkOrderProductVariationExist(Integer id) {
        if (!orderProductVariationRepository.existsById(id)) {
            throw new AppEx(ErrorCode.ORDER_PRODUCT_VARIATION_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional
    public OrderProductVariationResponse createOrderProductVariation(OrderProductVariationRequest request) {
        OrderProductVariation orderProductVariation = orderProductVariationMapper.toEntity(request);
        OrderProductVariation saved = orderProductVariationRepository.save(orderProductVariation);
        return orderProductVariationMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OrderProductVariationResponse updateOrderProductVariation(Integer id, OrderProductVariationRequest request) {
        OrderProductVariation orderProductVariation = orderProductVariationRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.ORDER_PRODUCT_VARIATION_NOT_FOUND));
        orderProductVariationMapper.updateEntity(request, orderProductVariation);
        OrderProductVariation updated = orderProductVariationRepository.save(orderProductVariation);
        return orderProductVariationMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteOrderProductVariation(Integer id) {
        checkOrderProductVariationExist(id);
        orderProductVariationRepository.deleteById(id);
    }

    @Override
    public OrderProductVariationResponse getOrderProductVariationById(Integer id) {
        OrderProductVariation orderProductVariation = orderProductVariationRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.ORDER_PRODUCT_VARIATION_NOT_FOUND));
        return orderProductVariationMapper.toResponse(orderProductVariation);
    }

    @Override
    public List<OrderProductVariationResponse> getAllOrderProductVariations() {
        return orderProductVariationRepository.findAll()
                .stream()
                .map(orderProductVariationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
