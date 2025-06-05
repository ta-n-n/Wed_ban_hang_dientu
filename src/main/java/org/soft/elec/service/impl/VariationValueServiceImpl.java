package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.VariationValueRequest;
import org.soft.elec.entity.dto.response.VariationValueResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.VariationValueMapper;
import org.soft.elec.entity.models.VariationValue;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.VariationValueRepository;
import org.soft.elec.service.VariationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VariationValueServiceImpl implements VariationValueService {

  @Autowired private VariationValueRepository variationValueRepository;

  @Autowired private VariationValueMapper variationValueMapper;

  private void checkVariationValueExist(Integer id) {
    if (!variationValueRepository.existsById(id)) {
      throw new AppEx(ErrorCode.VARIATION_VALUE_ALREADY_EXISTS);
    }
  }

  @Override
  @Transactional
  public VariationValueResponse createVariationValue(VariationValueRequest request) {
    VariationValue variationValue = variationValueMapper.toEntity(request);
    VariationValue saved = variationValueRepository.save(variationValue);
    return variationValueMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public VariationValueResponse updateVariationValue(Integer id, VariationValueRequest request) {
    VariationValue variationValue =
        variationValueRepository
            .findById(id)
            .orElseThrow(() -> new AppEx(ErrorCode.VARIATION_VALUE_NOT_FOUND));
    variationValueMapper.updateEntity(request, variationValue);
    VariationValue updated = variationValueRepository.save(variationValue);
    return variationValueMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteVariationValue(Integer id) {
    checkVariationValueExist(id);
    variationValueRepository.deleteById(id);
  }

  @Override
  public VariationValueResponse getVariationValueById(Integer id) {
    VariationValue variationValue =
        variationValueRepository
            .findById(id)
            .orElseThrow(() -> new AppEx(ErrorCode.VARIATION_VALUE_NOT_FOUND));
    return variationValueMapper.toResponse(variationValue);
  }

  @Override
  public List<VariationValueResponse> getAllVariationValues() {
    return variationValueRepository.findAll().stream()
        .map(variationValueMapper::toResponse)
        .collect(Collectors.toList());
  }
}
