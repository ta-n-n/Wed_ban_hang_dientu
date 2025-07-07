package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.request.search.ProductVariantSearchRequest;
import org.soft.elec.entity.dto.response.ProductVariantResponse;
import org.soft.elec.entity.mapper.ProductVariantMapper;
import org.soft.elec.entity.models.Product;
import org.soft.elec.entity.models.ProductVariant;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.ProductRepository;
import org.soft.elec.repository.ProductVariantRepository;
import org.soft.elec.service.ProductVariantService;
import org.soft.elec.specification.ProductVariantSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

  private final ProductVariantRepository productVariantRepository;
  private final ProductVariantMapper productVariantMapper;
  private final ProductRepository productRepository;

  @Override
  @Transactional
  public ProductVariantResponse createProductVariant(ProductVariantRequest request) {
    ProductVariant productVariant = productVariantMapper.toEntity(request);
    productVariant.setProduct(getProductById(request.getProductId()));
    ProductVariant saved = productVariantRepository.save(productVariant);
    return productVariantMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public ProductVariantResponse updateProductVariant(Integer id, ProductVariantRequest request) {
    ProductVariant variant =
        productVariantRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));

    productVariantMapper.updateEntity(request, variant);
    variant.setProduct(getProductById(request.getProductId()));
    ProductVariant saved = productVariantRepository.save(variant);
    return productVariantMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public void deleteProductVariant(Integer id) {
    ProductVariant variant =
        productVariantRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));
    productVariantRepository.delete(variant);
  }

  @Override
  public ProductVariantResponse getProductVariantById(Integer id) {
    ProductVariant variant =
        productVariantRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));
    return productVariantMapper.toResponse(variant);
  }

  @Override
  public List<ProductVariantResponse> getAllProductVariants() {
    return productVariantRepository.findAll().stream()
        .map(productVariantMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<ProductVariantResponse> searchProductVariants(ProductVariantSearchRequest request) {
    Specification<ProductVariant> spec = null;

    spec = SpecUtil.add(spec, ProductVariantSpecification.hasProductId(request.getProductId()));
    spec = SpecUtil.add(spec, ProductVariantSpecification.keywordContains(request.getKeyword()));
    spec = SpecUtil.add(spec, ProductVariantSpecification.isActive(request.getIsActive()));
    spec = SpecUtil.add(spec, ProductVariantSpecification.isInStock(request.getInStock()));
    spec =
        SpecUtil.add(
            spec,
            ProductVariantSpecification.priceBetween(request.getMinPrice(), request.getMaxPrice()));
    spec =
        SpecUtil.add(spec, ProductVariantSpecification.deletedBefore(request.getDeletedBefore()));

    Pageable pageable = PageUtil.getPageable(request);
    Page<ProductVariant> page = productVariantRepository.findAll(spec, pageable);

    return page.map(productVariantMapper::toResponse);
  }

  // === Helper method ===
  private Product getProductById(Integer id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
  }
}
