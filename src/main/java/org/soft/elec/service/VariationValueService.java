package org.soft.elec.service;

import org.soft.elec.entity.dto.request.VariationValueRequest;
import org.soft.elec.entity.dto.response.VariationValueResponse;
import java.util.List;

public interface VariationValueService {
    VariationValueResponse createVariationValue(VariationValueRequest request);
    VariationValueResponse updateVariationValue(Integer id, VariationValueRequest request);
    void deleteVariationValue(Integer id);
    VariationValueResponse getVariationValueById(Integer id);
    List<VariationValueResponse> getAllVariationValues();
}
