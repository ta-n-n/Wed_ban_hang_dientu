package org.soft.elec.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariationRequest {
    private String name;
    private String type;
    private Boolean isGlobal;
    private Integer position;
}
