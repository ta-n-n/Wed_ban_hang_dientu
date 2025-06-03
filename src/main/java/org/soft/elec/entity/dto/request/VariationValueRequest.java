package org.soft.elec.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariationValueRequest {
    private String label;
    private Integer variationId;
    private String value;
    private Integer position;
}
