package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.OptionRequest;
import org.soft.elec.entity.dto.request.search.OptionSearchRequest;
import org.soft.elec.entity.dto.response.OptionResponse;
import org.springframework.data.domain.Page;

public interface OptionService {
  OptionResponse createOption(OptionRequest request);

  OptionResponse updateOption(Integer id, OptionRequest request);

  void deleteOption(Integer id);

  OptionResponse getOptionById(Integer id);

  List<OptionResponse> getAllOptions();

  Page<OptionResponse> searchOptions(OptionSearchRequest request);
}
