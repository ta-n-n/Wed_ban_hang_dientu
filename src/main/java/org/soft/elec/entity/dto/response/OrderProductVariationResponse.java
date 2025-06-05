package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.OrderProduct;
import org.soft.elec.entity.models.Variation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductVariationResponse {
  private Integer id;
  private OrderProduct orderProduct;
  private Variation variation;
  private String type;
  private String value;
}
