package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.Variation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariationValueResponse {
    private Integer id;
    private String label;
    private Variation variation;
    private String value;
    private Integer position;
}
