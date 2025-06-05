package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.OptionRequest;
import org.soft.elec.entity.dto.response.OptionResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.OptionMapper;
import org.soft.elec.entity.models.Option;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.OptionRepository;
import org.soft.elec.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptionServiceImpl implements OptionService {

  @Autowired private OptionRepository optionRepository;

  @Autowired private OptionMapper optionMapper;

  private void checkOptionExist(Integer id) {
    if (!optionRepository.existsById(id)) {
      throw new AppEx(ErrorCode.OPTION_ALREADY_EXISTS);
    }
  }

  @Override
  @Transactional
  public OptionResponse createOption(OptionRequest request) {
    Option option = optionMapper.toEntity(request);
    Option saved = optionRepository.save(option);
    return optionMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public OptionResponse updateOption(Integer id, OptionRequest request) {
    Option option =
        optionRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.OPTION_NOT_FOUND));
    optionMapper.updateEntity(request, option);
    Option updated = optionRepository.save(option);
    return optionMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOption(Integer id) {
    checkOptionExist(id);
    optionRepository.deleteById(id);
  }

  @Override
  public OptionResponse getOptionById(Integer id) {
    Option option =
        optionRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.OPTION_NOT_FOUND));
    return optionMapper.toResponse(option);
  }

  @Override
  public List<OptionResponse> getAllOptions() {
    return optionRepository.findAll().stream()
        .map(optionMapper::toResponse)
        .collect(Collectors.toList());
  }
}
