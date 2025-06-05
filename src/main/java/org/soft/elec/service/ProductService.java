package org.soft.elec.service;

import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Integer id, ProductRequest request);
    void deleteProduct(Integer id);
    ProductResponse getProductById(Integer id);
    List<ProductResponse> getAllProducts();
}
