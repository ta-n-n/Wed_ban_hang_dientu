package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.OptionValueRequest;
import org.soft.elec.entity.dto.response.OptionValueResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.OptionValueMapper;
import org.soft.elec.entity.models.OptionValue;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.OptionValueRepository;
import org.soft.elec.service.OptionValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptionValueServiceImpl implements OptionValueService {

  @Autowired private OptionValueRepository optionValueRepository;

  @Autowired private OptionValueMapper optionValueMapper;

  private void checkOptionValueExist(Integer id) {
    if (!optionValueRepository.existsById(id)) {
      throw new AppEx(ErrorCode.OPTION_VALUE_NOT_FOUND);
    }
  }

  @Override
  @Transactional
  public OptionValueResponse createOptionValue(OptionValueRequest request) {
    OptionValue optionValue = optionValueMapper.toEntity(request);
    OptionValue saved = optionValueRepository.save(optionValue);
    return optionValueMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public OptionValueResponse updateOptionValue(Integer id, OptionValueRequest request) {
    OptionValue optionValue =
        optionValueRepository
            .findById(id)
            .orElseThrow(() -> new AppEx(ErrorCode.OPTION_VALUE_NOT_FOUND));
    optionValueMapper.updateEntity(request, optionValue);
    OptionValue updated = optionValueRepository.save(optionValue);
    return optionValueMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOptionValue(Integer id) {
    checkOptionValueExist(id);
    optionValueRepository.deleteById(id);
  }

  @Override
  public OptionValueResponse getOptionValueById(Integer id) {
    OptionValue optionValue =
        optionValueRepository
            .findById(id)
            .orElseThrow(() -> new AppEx(ErrorCode.OPTION_VALUE_NOT_FOUND));
    return optionValueMapper.toResponse(optionValue);
  }

  @Override
  public List<OptionValueResponse> getAllOptionValues() {
    return optionValueRepository.findAll().stream()
        .map(optionValueMapper::toResponse)
        .collect(Collectors.toList());
  }
}
