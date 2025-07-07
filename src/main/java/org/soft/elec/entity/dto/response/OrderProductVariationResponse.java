package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductVariationResponse {
  private Integer id;
  private OrderProductResponse orderProduct;
  private VariationResponse variation;
  private String type;
  private String value;
}
