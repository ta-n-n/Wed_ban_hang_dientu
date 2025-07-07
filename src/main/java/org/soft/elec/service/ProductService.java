package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.request.search.ProductSearchRequest;
import org.soft.elec.entity.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
  ProductResponse createProduct(ProductRequest request);

  ProductResponse updateProduct(Integer id, ProductRequest request);

  void deleteProduct(Integer id);

  ProductResponse getProductById(Integer id);

  List<ProductResponse> getAllProducts();

  Page<ProductResponse> searchProducts(ProductSearchRequest request);
}
