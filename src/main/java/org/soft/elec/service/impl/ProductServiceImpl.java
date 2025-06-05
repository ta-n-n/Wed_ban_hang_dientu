package org.soft.elec.service.impl;

import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.response.ProductResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.ProductMapper;
import org.soft.elec.entity.models.Product;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.ProductRepository;
import org.soft.elec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    private void checkProductExist(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new AppEx(ErrorCode.PRODUCT_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Integer id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateEntity(request, product);
        Product updated = productRepository.save(product);
        return productMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        checkProductExist(id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
}
