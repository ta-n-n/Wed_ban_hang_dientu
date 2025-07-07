package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OrderProductRequest;
import org.soft.elec.entity.dto.request.search.OrderProductSearchRequest;
import org.soft.elec.entity.dto.response.OrderProductResponse;
import org.soft.elec.entity.mapper.OrderProductMapper;
import org.soft.elec.entity.models.Order;
import org.soft.elec.entity.models.OrderProduct;
import org.soft.elec.entity.models.Product;
import org.soft.elec.entity.models.ProductVariant;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.OrderProductRepository;
import org.soft.elec.repository.OrderRepository;
import org.soft.elec.repository.ProductRepository;
import org.soft.elec.repository.ProductVariantRepository;
import org.soft.elec.service.OrderProductService;
import org.soft.elec.specification.OrderProductSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {

  private final OrderProductRepository orderProductRepository;
  private final OrderProductMapper orderProductMapper;
  private final ProductRepository productRepository;
  private final ProductVariantRepository productVariantRepository;
  private final OrderRepository orderRepository;

  @Override
  @Transactional
  public OrderProductResponse createOrderProduct(OrderProductRequest request) {
    Order order =
        orderRepository
            .findById(request.getOrderId())
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    Product product =
        productRepository
            .findById(request.getProductId())
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    ProductVariant variant = null;
    if (request.getProductVariantId() != null) {
      variant =
          productVariantRepository
              .findById(request.getProductVariantId())
              .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));
    }
    OrderProduct orderProduct = orderProductMapper.toEntity(request);
    orderProduct.setOrder(order);
    orderProduct.setProduct(product);
    orderProduct.setProductVariant(variant);
    OrderProduct saved = orderProductRepository.save(orderProduct);
    return orderProductMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public OrderProductResponse updateOrderProduct(Integer id, OrderProductRequest request) {
    OrderProduct existing =
        orderProductRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_NOT_FOUND));
    Order order =
        orderRepository
            .findById(request.getOrderId())
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    Product product =
        productRepository
            .findById(request.getProductId())
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    ProductVariant variant = null;
    if (request.getProductVariantId() != null) {
      variant =
          productVariantRepository
              .findById(request.getProductVariantId())
              .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));
    }
    orderProductMapper.updateEntity(request, existing);
    existing.setOrder(order);
    existing.setProduct(product);
    existing.setProductVariant(variant);

    OrderProduct updated = orderProductRepository.save(existing);
    return orderProductMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOrderProduct(Integer id) {
    OrderProduct existing =
        orderProductRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_NOT_FOUND));
    orderProductRepository.delete(existing);
  }

  @Override
  public OrderProductResponse getOrderProductById(Integer id) {
    OrderProduct orderProduct =
        orderProductRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ORDER_PRODUCT_NOT_FOUND));
    return orderProductMapper.toResponse(orderProduct);
  }

  @Override
  public List<OrderProductResponse> getAllOrderProducts() {
    return orderProductRepository.findAll().stream()
        .map(orderProductMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<OrderProductResponse> searchOrderProducts(OrderProductSearchRequest request) {
    Specification<OrderProduct> spec = null;
    spec = SpecUtil.add(spec, OrderProductSpecification.hasOrderId(request.getOrderId()));
    spec = SpecUtil.add(spec, OrderProductSpecification.hasProductId(request.getProductId()));
    spec =
        SpecUtil.add(
            spec, OrderProductSpecification.hasProductVariantId(request.getProductVariantId()));
    spec = SpecUtil.add(spec, OrderProductSpecification.hasMinLineTotal(request.getMinLineTotal()));
    spec = SpecUtil.add(spec, OrderProductSpecification.hasMaxLineTotal(request.getMaxLineTotal()));
    Pageable pageable = PageUtil.getPageable(request);
    return orderProductRepository.findAll(spec, pageable).map(orderProductMapper::toResponse);
  }
}
