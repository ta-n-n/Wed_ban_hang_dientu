package org.soft.elec.service;

import org.soft.elec.entity.dto.request.VariationRequest;
import org.soft.elec.entity.dto.response.VariationResponse;
import java.util.List;

public interface VariationService {
    VariationResponse createVariation(VariationRequest request);
    VariationResponse updateVariation(Integer id, VariationRequest request);
    void deleteVariation(Integer id);
    VariationResponse getVariationById(Integer id);
    List<VariationResponse> getAllVariations();
}
