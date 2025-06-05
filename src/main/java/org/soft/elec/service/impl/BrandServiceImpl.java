package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.BrandRequest;
import org.soft.elec.entity.dto.response.BrandResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.BrandMapper;
import org.soft.elec.entity.models.Brand;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.BrandRepository;
import org.soft.elec.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrandServiceImpl implements BrandService {

  @Autowired private BrandRepository brandRepository;

  @Autowired private BrandMapper brandMapper;

  private void checkBrandExist(Integer id) {
    if (!brandRepository.existsById(id)) {
      throw new AppEx(ErrorCode.BRAND_ALREADY_EXISTS);
    }
  }

  @Override
  @Transactional
  public BrandResponse createBrand(BrandRequest request) {
    Brand brand = brandMapper.toEntity(request);
    Brand saved = brandRepository.save(brand);
    return brandMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public BrandResponse updateBrand(Integer id, BrandRequest request) {
    Brand brand =
        brandRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.BRAND_NOT_FOUND));
    brandMapper.updateEntity(request, brand);
    Brand updated = brandRepository.save(brand);
    return brandMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteBrand(Integer id) {
    checkBrandExist(id);
    brandRepository.deleteById(id);
  }

  @Override
  public BrandResponse getBrandById(Integer id) {
    Brand brand =
        brandRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.BRAND_NOT_FOUND));
    return brandMapper.toResponse(brand);
  }

  @Override
  public List<BrandResponse> getAllBrands() {
    return brandRepository.findAll().stream()
        .map(brandMapper::toResponse)
        .collect(Collectors.toList());
  }
}
