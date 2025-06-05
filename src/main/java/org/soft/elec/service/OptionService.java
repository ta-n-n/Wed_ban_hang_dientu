package org.soft.elec.service;

import org.soft.elec.entity.dto.request.OptionRequest;
import org.soft.elec.entity.dto.response.OptionResponse;
import java.util.List;

public interface OptionService {
    OptionResponse createOption(OptionRequest request);
    OptionResponse updateOption(Integer id, OptionRequest request);
    void deleteOption(Integer id);
    OptionResponse getOptionById(Integer id);
    List<OptionResponse> getAllOptions();
}
