package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OptionRequest;
import org.soft.elec.entity.dto.request.search.OptionSearchRequest;
import org.soft.elec.entity.dto.response.OptionResponse;
import org.soft.elec.entity.mapper.OptionMapper;
import org.soft.elec.entity.models.Option;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.OptionRepository;
import org.soft.elec.service.OptionService;
import org.soft.elec.specification.OptionSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

  private final OptionRepository optionRepository;
  private final OptionMapper optionMapper;

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
        optionRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.OPTION_NOT_FOUND));
    optionMapper.updateEntity(request, option);
    Option updated = optionRepository.save(option);
    return optionMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOption(Integer id) {
    Option option =
        optionRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.OPTION_NOT_FOUND));
    optionRepository.delete(option);
  }

  @Override
  public OptionResponse getOptionById(Integer id) {
    Option option =
        optionRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.OPTION_NOT_FOUND));
    return optionMapper.toResponse(option);
  }

  @Override
  public List<OptionResponse> getAllOptions() {
    return optionRepository.findAll().stream()
        .map(optionMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<OptionResponse> searchOptions(OptionSearchRequest request) {
    Specification<Option> spec = null;
    spec = SpecUtil.add(spec, OptionSpecification.hasType(request.getType()));
    spec = SpecUtil.add(spec, OptionSpecification.isRequired(request.getIsRequired()));
    spec = SpecUtil.add(spec, OptionSpecification.isGlobal(request.getIsGlobal()));
    Pageable pageable = PageUtil.getPageable(request);
    return optionRepository.findAll(spec, pageable).map(optionMapper::toResponse);
  }
}
