package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.request.search.ProductVariantSearchRequest;
import org.soft.elec.entity.dto.response.ProductVariantResponse;
import org.springframework.data.domain.Page;

public interface ProductVariantService {
  ProductVariantResponse createProductVariant(ProductVariantRequest request);

  ProductVariantResponse updateProductVariant(Integer id, ProductVariantRequest request);

  void deleteProductVariant(Integer id);

  ProductVariantResponse getProductVariantById(Integer id);

  List<ProductVariantResponse> getAllProductVariants();

  Page<ProductVariantResponse> searchProductVariants(ProductVariantSearchRequest request);
}
