package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.VariationValueRequest;
import org.soft.elec.entity.dto.request.search.VariationValueSearchRequest;
import org.soft.elec.entity.dto.response.VariationValueResponse;
import org.soft.elec.entity.mapper.VariationValueMapper;
import org.soft.elec.entity.models.Variation;
import org.soft.elec.entity.models.VariationValue;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.VariationRepository;
import org.soft.elec.repository.VariationValueRepository;
import org.soft.elec.service.VariationValueService;
import org.soft.elec.specification.VariationValueSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VariationValueServiceImpl implements VariationValueService {

  private final VariationValueRepository variationValueRepository;
  private final VariationValueMapper variationValueMapper;
  private final VariationRepository variationRepository;

  @Override
  @Transactional
  public VariationValueResponse createVariationValue(VariationValueRequest request) {
    Variation variation = getVariationOrThrow(request.getVariationId());
    VariationValue variationValue = variationValueMapper.toEntity(request);
    variationValue.setVariation(variation);
    VariationValue saved = variationValueRepository.save(variationValue);
    return variationValueMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public VariationValueResponse updateVariationValue(Integer id, VariationValueRequest request) {
    VariationValue variationValue = getVariationValueOrThrow(id);
    variationValueMapper.updateEntity(request, variationValue);
    VariationValue updated = variationValueRepository.save(variationValue);
    return variationValueMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteVariationValue(Integer id) {
    VariationValue variationValue = getVariationValueOrThrow(id);
    variationValueRepository.delete(variationValue);
  }

  @Override
  public VariationValueResponse getVariationValueById(Integer id) {
    VariationValue variationValue = getVariationValueOrThrow(id);
    return variationValueMapper.toResponse(variationValue);
  }

  @Override
  public List<VariationValueResponse> getAllVariationValues() {
    return variationValueRepository.findAll().stream()
        .map(variationValueMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<VariationValueResponse> searchVariationValues(VariationValueSearchRequest request) {
    Specification<VariationValue> spec = null;

    spec = SpecUtil.add(spec, VariationValueSpecification.hasLabel(request.getLabel()));
    spec = SpecUtil.add(spec, VariationValueSpecification.hasValue(request.getValue()));
    spec = SpecUtil.add(spec, VariationValueSpecification.hasVariationId(request.getVariationId()));

    Pageable pageable = PageUtil.getPageable(request);

    return variationValueRepository.findAll(spec, pageable).map(variationValueMapper::toResponse);
  }

  // ==== Helpers ====
  private VariationValue getVariationValueOrThrow(Integer id) {
    return variationValueRepository
        .findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.VARIATION_VALUE_NOT_FOUND));
  }

  private Variation getVariationOrThrow(Integer id) {
    return variationRepository
        .findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.VARIATION_NOT_FOUND));
  }
}
