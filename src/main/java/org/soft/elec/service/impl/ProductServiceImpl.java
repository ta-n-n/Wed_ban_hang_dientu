package org.soft.elec.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.request.search.ProductSearchRequest;
import org.soft.elec.entity.dto.response.ProductResponse;
import org.soft.elec.entity.mapper.ProductMapper;
import org.soft.elec.entity.models.*;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.*;
import org.soft.elec.service.ProductService;
import org.soft.elec.specification.ProductSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final BrandRepository brandRepository;
  private final CategoryRepository categoryRepository;
  private final VariationRepository variationRepository;
  private final OptionRepository optionRepository;
  private final ProductVariantRepository productVariantRepository;

  @Override
  @Transactional
  public ProductResponse createProduct(ProductRequest request) {
    Product product = productMapper.toEntity(request);
    product.setBrand(getBrandById(request.getBrandId()));
    product.setCategories(getCategoriesByIds(request.getCategoryIds()));
    product.setVariations(getVariationsByIds(request.getVariationIds()));
    product.setOptions(getOptionsByIds(request.getOptionIds()));
    product.setVariants(getProductVariantsByIds(request.getVariantIds()));

    Product saved = productRepository.save(product);
    return productMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public ProductResponse updateProduct(Integer id, ProductRequest request) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

    productMapper.updateEntity(request, product);
    product.setBrand(getBrandById(request.getBrandId()));
    product.setCategories(getCategoriesByIds(request.getCategoryIds()));
    product.setVariations(getVariationsByIds(request.getVariationIds()));
    product.setOptions(getOptionsByIds(request.getOptionIds()));
    product.setVariants(getProductVariantsByIds(request.getVariantIds()));

    Product updated = productRepository.save(product);
    return productMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteProduct(Integer id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    productRepository.delete(product);
  }

  @Override
  public ProductResponse getProductById(Integer id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    return productMapper.toResponse(product);
  }

  @Override
  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream()
        .map(productMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<ProductResponse> searchProducts(ProductSearchRequest request) {
    Specification<Product> spec = null;

    spec = SpecUtil.add(spec, ProductSpecification.keywordContains(request.getKeyword()));
    spec = SpecUtil.add(spec, ProductSpecification.hasBrand(request.getBrandId()));
    spec = SpecUtil.add(spec, ProductSpecification.isActive(request.getIsActive()));
    spec = SpecUtil.add(spec, ProductSpecification.inStock(request.getInStock()));
    spec =
        SpecUtil.add(
            spec, ProductSpecification.priceBetween(request.getMinPrice(), request.getMaxPrice()));
    spec =
        SpecUtil.add(
            spec, ProductSpecification.newDateBetween(request.getNewFrom(), request.getNewTo()));

    Pageable pageable = PageUtil.getPageable(request);
    Page<Product> productPage = productRepository.findAll(spec, pageable);

    return productPage.map(productMapper::toResponse);
  }

  // === Helper methods ===

  private Brand getBrandById(Integer id) {
    return brandRepository
        .findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
  }

  private List<Category> getCategoriesByIds(List<Integer> ids) {
    if (ids == null || ids.isEmpty()) return new ArrayList<>();
    List<Category> list = categoryRepository.findAllById(ids);
    if (list.size() != ids.size()) throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
    return list;
  }

  private List<Variation> getVariationsByIds(List<Integer> ids) {
    if (ids == null || ids.isEmpty()) return new ArrayList<>();
    List<Variation> list = variationRepository.findAllById(ids);
    if (list.size() != ids.size()) throw new AppException(ErrorCode.VARIATION_NOT_FOUND);
    return list;
  }

  private List<Option> getOptionsByIds(List<Integer> ids) {
    if (ids == null || ids.isEmpty()) return new ArrayList<>();
    List<Option> list = optionRepository.findAllById(ids);
    if (list.size() != ids.size()) throw new AppException(ErrorCode.OPTION_NOT_FOUND);
    return list;
  }

  private List<ProductVariant> getProductVariantsByIds(List<Integer> ids) {
    if (ids == null || ids.isEmpty()) return new ArrayList<>();
    List<ProductVariant> list = productVariantRepository.findAllById(ids);
    if (list.size() != ids.size()) throw new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND);
    return list;
  }
}
