package org.soft.elec.service;

import org.soft.elec.entity.dto.request.BrandRequest;
import org.soft.elec.entity.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {
    BrandResponse createBrand(BrandRequest request);
    BrandResponse updateBrand(Integer id, BrandRequest request);
    void deleteBrand(Integer id);
    BrandResponse getBrandById(Integer id);
    List<BrandResponse> getAllBrands();
}
