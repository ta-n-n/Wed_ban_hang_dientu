package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.VariationRequest;
import org.soft.elec.entity.dto.response.VariationResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.VariationMapper;
import org.soft.elec.entity.models.Variation;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.VariationRepository;
import org.soft.elec.service.VariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VariationServiceImpl implements VariationService {

  @Autowired private VariationRepository variationRepository;

  @Autowired private VariationMapper variationMapper;

  private void checkVariationExist(Integer id) {
    if (!variationRepository.existsById(id)) {
      throw new AppEx(ErrorCode.VARIATION_ALREADY_EXISTS);
    }
  }

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
    Variation variation =
        variationRepository
            .findById(id)
            .orElseThrow(() -> new AppEx(ErrorCode.VARIATION_NOT_FOUND));
    variationMapper.updateEntity(request, variation);
    Variation updated = variationRepository.save(variation);
    return variationMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteVariation(Integer id) {
    checkVariationExist(id);
    variationRepository.deleteById(id);
  }

  @Override
  public VariationResponse getVariationById(Integer id) {
    Variation variation =
        variationRepository
            .findById(id)
            .orElseThrow(() -> new AppEx(ErrorCode.VARIATION_NOT_FOUND));
    return variationMapper.toResponse(variation);
  }

  @Override
  public List<VariationResponse> getAllVariations() {
    return variationRepository.findAll().stream()
        .map(variationMapper::toResponse)
        .collect(Collectors.toList());
  }
}
