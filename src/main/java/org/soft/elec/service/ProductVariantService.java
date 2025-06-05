package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.response.ProductVariantResponse;

public interface ProductVariantService {
  ProductVariantResponse createProductVariant(ProductVariantRequest request);

  ProductVariantResponse updateProductVariant(Integer id, ProductVariantRequest request);

  void deleteProductVariant(Integer id);

  ProductVariantResponse getProductVariantById(Integer id);

  List<ProductVariantResponse> getAllProductVariants();
}
