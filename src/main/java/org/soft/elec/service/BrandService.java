package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.BrandRequest;
import org.soft.elec.entity.dto.request.search.BrandSearchRequest;
import org.soft.elec.entity.dto.response.BrandResponse;
import org.springframework.data.domain.Page;

public interface BrandService {
  BrandResponse createBrand(BrandRequest request);

  BrandResponse updateBrand(Integer id, BrandRequest request);

  void deleteBrand(Integer id);

  BrandResponse getBrandById(Integer id);

  List<BrandResponse> getAllBrands();

  Page<BrandResponse> searchBrands(BrandSearchRequest request);
}
