package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.VariationValueRequest;
import org.soft.elec.entity.dto.request.search.VariationValueSearchRequest;
import org.soft.elec.entity.dto.response.VariationValueResponse;
import org.springframework.data.domain.Page;

public interface VariationValueService {
  VariationValueResponse createVariationValue(VariationValueRequest request);

  VariationValueResponse updateVariationValue(Integer id, VariationValueRequest request);

  void deleteVariationValue(Integer id);

  VariationValueResponse getVariationValueById(Integer id);

  List<VariationValueResponse> getAllVariationValues();

  Page<VariationValueResponse> searchVariationValues(VariationValueSearchRequest request);
}
