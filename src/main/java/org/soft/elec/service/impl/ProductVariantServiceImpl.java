package org.soft.elec.service.impl;

import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.response.ProductVariantResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.ProductVariantMapper;
import org.soft.elec.entity.models.ProductVariant;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.ProductVariantRepository;
import org.soft.elec.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private ProductVariantMapper productVariantMapper;

    private void checkProductVariantExist(Integer id) {
        if (!productVariantRepository.existsById(id)) {
            throw new AppEx(ErrorCode.PRODUCT_VARIANT_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional
    public ProductVariantResponse createProductVariant(ProductVariantRequest request) {
        ProductVariant productVariant = productVariantMapper.toEntity(request);
        ProductVariant saved = productVariantRepository.save(productVariant);
        return productVariantMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ProductVariantResponse updateProductVariant(Integer id, ProductVariantRequest request) {
        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));
        productVariantMapper.updateEntity(request, productVariant);
        ProductVariant updated = productVariantRepository.save(productVariant);
        return productVariantMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteProductVariant(Integer id) {
        checkProductVariantExist(id);
        productVariantRepository.deleteById(id);
    }

    @Override
    public ProductVariantResponse getProductVariantById(Integer id) {
        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));
        return productVariantMapper.toResponse(productVariant);
    }

    @Override
    public List<ProductVariantResponse> getAllProductVariants() {
        return productVariantRepository.findAll()
                .stream()
                .map(productVariantMapper::toResponse)
                .collect(Collectors.toList());
    }
}
