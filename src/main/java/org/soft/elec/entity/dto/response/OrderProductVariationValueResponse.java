package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.OrderProductVariation;
import org.soft.elec.entity.models.VariationValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductVariationValueResponse {
    private Integer id;
    private OrderProductVariation orderProductVariation;
    private VariationValue variationValue;
}
