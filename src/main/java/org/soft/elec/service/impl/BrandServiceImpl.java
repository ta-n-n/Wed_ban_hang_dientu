package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.BrandRequest;
import org.soft.elec.entity.dto.request.search.BrandSearchRequest;
import org.soft.elec.entity.dto.response.BrandResponse;
import org.soft.elec.entity.mapper.BrandMapper;
import org.soft.elec.entity.models.Brand;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.BrandRepository;
import org.soft.elec.service.BrandService;
import org.soft.elec.specification.BrandSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;
  private final BrandMapper brandMapper;

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
        brandRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
    brandMapper.updateEntity(request, brand);
    Brand updated = brandRepository.save(brand);
    return brandMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteBrand(Integer id) {
    Brand brand =
        brandRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
    brandRepository.delete(brand);
  }

  @Override
  public BrandResponse getBrandById(Integer id) {
    Brand brand =
        brandRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
    return brandMapper.toResponse(brand);
  }

  @Override
  public List<BrandResponse> getAllBrands() {
    return brandRepository.findAll().stream()
        .map(brandMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<BrandResponse> searchBrands(BrandSearchRequest request) {
    Specification<Brand> spec = null;
    spec = SpecUtil.add(spec, BrandSpecification.nameContains(request.getKeyword()));
    spec = SpecUtil.add(spec, BrandSpecification.isActive(request.getIsActive()));
    Pageable pageable = PageUtil.getPageable(request);
    return brandRepository.findAll(spec, pageable).map(brandMapper::toResponse);
  }
}
