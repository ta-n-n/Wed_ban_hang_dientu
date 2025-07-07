package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.VariationRequest;
import org.soft.elec.entity.dto.request.search.VariationSearchRequest;
import org.soft.elec.entity.dto.response.VariationResponse;
import org.springframework.data.domain.Page;

public interface VariationService {
  VariationResponse createVariation(VariationRequest request);

  VariationResponse updateVariation(Integer id, VariationRequest request);

  void deleteVariation(Integer id);

  VariationResponse getVariationById(Integer id);

  List<VariationResponse> getAllVariations();

  Page<VariationResponse> searchVariations(VariationSearchRequest request);
}
