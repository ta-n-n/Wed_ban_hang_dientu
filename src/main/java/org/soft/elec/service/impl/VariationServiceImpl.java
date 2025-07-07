package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.VariationRequest;
import org.soft.elec.entity.dto.request.search.VariationSearchRequest;
import org.soft.elec.entity.dto.response.VariationResponse;
import org.soft.elec.entity.mapper.VariationMapper;
import org.soft.elec.entity.models.Variation;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.VariationRepository;
import org.soft.elec.service.VariationService;
import org.soft.elec.specification.VariationSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VariationServiceImpl implements VariationService {

  private final VariationRepository variationRepository;
  private final VariationMapper variationMapper;

  @Override
  @Transactional
  public VariationResponse createVariation(VariationRequest request) {
    Variation variation = variationMapper.toEntity(request);
    Variation saved = variationRepository.save(variation);
    return variationMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public VariationResponse updateVariation(Integer id, VariationRequest request) {
    Variation variation = getVariationOrThrow(id);
    variationMapper.updateEntity(request, variation);
    Variation updated = variationRepository.save(variation);
    return variationMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteVariation(Integer id) {
    Variation variation = getVariationOrThrow(id);
    variationRepository.delete(variation);
  }

  @Override
  public VariationResponse getVariationById(Integer id) {
    Variation variation = getVariationOrThrow(id);
    return variationMapper.toResponse(variation);
  }

  @Override
  public List<VariationResponse> getAllVariations() {
    return variationRepository.findAll().stream()
        .map(variationMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<VariationResponse> searchVariations(VariationSearchRequest request) {
    Specification<Variation> spec = null;

    spec = SpecUtil.add(spec, VariationSpecification.hasName(request.getName()));
    spec = SpecUtil.add(spec, VariationSpecification.hasType(request.getType()));
    spec = SpecUtil.add(spec, VariationSpecification.isGlobal(request.getIsGlobal()));

    Pageable pageable = PageUtil.getPageable(request);

    return variationRepository.findAll(spec, pageable).map(variationMapper::toResponse);
  }

  // === Helper ===
  private Variation getVariationOrThrow(Integer id) {
    return variationRepository
        .findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.VARIATION_NOT_FOUND));
  }
}
