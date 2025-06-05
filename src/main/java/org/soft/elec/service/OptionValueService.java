package org.soft.elec.service;

import org.soft.elec.entity.dto.request.OptionValueRequest;
import org.soft.elec.entity.dto.response.OptionValueResponse;
import java.util.List;

public interface OptionValueService {
    OptionValueResponse createOptionValue(OptionValueRequest request);
    OptionValueResponse updateOptionValue(Integer id, OptionValueRequest request);
    void deleteOptionValue(Integer id);
    OptionValueResponse getOptionValueById(Integer id) ;
    List<OptionValueResponse> getAllOptionValues();
}
