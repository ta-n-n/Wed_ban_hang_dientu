package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.OptionValueRequest;
import org.soft.elec.entity.dto.request.search.OptionValueSearchRequest;
import org.soft.elec.entity.dto.response.OptionValueResponse;
import org.soft.elec.entity.mapper.OptionValueMapper;
import org.soft.elec.entity.models.Option;
import org.soft.elec.entity.models.OptionValue;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.OptionRepository;
import org.soft.elec.repository.OptionValueRepository;
import org.soft.elec.service.OptionValueService;
import org.soft.elec.specification.OptionValueSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OptionValueServiceImpl implements OptionValueService {

  private final OptionValueRepository optionValueRepository;
  private final OptionValueMapper optionValueMapper;
  private final OptionRepository optionRepository;

  @Override
  @Transactional
  public OptionValueResponse createOptionValue(OptionValueRequest request) {
    Option option =
        optionRepository
            .findById(request.getOptionId())
            .orElseThrow(() -> new AppException(ErrorCode.OPTION_NOT_FOUND));
    OptionValue optionValue = optionValueMapper.toEntity(request);
    optionValue.setOption(option);
    OptionValue saved = optionValueRepository.save(optionValue);
    return optionValueMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public OptionValueResponse updateOptionValue(Integer id, OptionValueRequest request) {
    OptionValue existing =
        optionValueRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.OPTION_VALUE_NOT_FOUND));
    Option option =
        optionRepository
            .findById(request.getOptionId())
            .orElseThrow(() -> new AppException(ErrorCode.OPTION_NOT_FOUND));
    existing.setOption(option);
    optionValueMapper.updateEntity(request, existing);

    OptionValue updated = optionValueRepository.save(existing);
    return optionValueMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteOptionValue(Integer id) {
    OptionValue existing =
        optionValueRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.OPTION_VALUE_NOT_FOUND));
    optionValueRepository.delete(existing);
  }

  @Override
  public OptionValueResponse getOptionValueById(Integer id) {
    OptionValue optionValue =
        optionValueRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.OPTION_VALUE_NOT_FOUND));
    return optionValueMapper.toResponse(optionValue);
  }

  @Override
  public List<OptionValueResponse> getAllOptionValues() {
    return optionValueRepository.findAll().stream()
        .map(optionValueMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<OptionValueResponse> searchOptionValues(OptionValueSearchRequest request) {
    Specification<OptionValue> spec = null;
    spec = SpecUtil.add(spec, OptionValueSpecification.hasOptionId(request.getOptionId()));
    spec = SpecUtil.add(spec, OptionValueSpecification.hasPriceType(request.getPriceType()));
    spec = SpecUtil.add(spec, OptionValueSpecification.hasMinPrice(request.getMinPrice()));
    spec = SpecUtil.add(spec, OptionValueSpecification.hasMaxPrice(request.getMaxPrice()));
    Pageable pageable = PageUtil.getPageable(request);
    Page<OptionValue> resultPage = optionValueRepository.findAll(spec, pageable);
    return resultPage.map(optionValueMapper::toResponse);
  }
}
