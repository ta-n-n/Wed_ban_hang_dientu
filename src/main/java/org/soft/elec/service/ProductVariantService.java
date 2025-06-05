package org.soft.elec.service;

import org.soft.elec.entity.dto.request.ProductVariantRequest;
import org.soft.elec.entity.dto.response.ProductVariantResponse;

import java.util.List;

public interface ProductVariantService {
    ProductVariantResponse createProductVariant(ProductVariantRequest request);
    ProductVariantResponse updateProductVariant(Integer id, ProductVariantRequest request);
    void deleteProductVariant(Integer id);
    ProductVariantResponse getProductVariantById(Integer id);
    List<ProductVariantResponse> getAllProductVariants();
}
