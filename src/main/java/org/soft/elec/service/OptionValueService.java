package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.OptionValueRequest;
import org.soft.elec.entity.dto.request.search.OptionValueSearchRequest;
import org.soft.elec.entity.dto.response.OptionValueResponse;
import org.springframework.data.domain.Page;

public interface OptionValueService {
  OptionValueResponse createOptionValue(OptionValueRequest request);

  OptionValueResponse updateOptionValue(Integer id, OptionValueRequest request);

  void deleteOptionValue(Integer id);

  OptionValueResponse getOptionValueById(Integer id);

  List<OptionValueResponse> getAllOptionValues();

  Page<OptionValueResponse> searchOptionValues(OptionValueSearchRequest request);
}
