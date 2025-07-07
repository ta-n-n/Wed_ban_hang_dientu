package org.soft.elec.entity.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductResponse {
  private Integer id;
  private Integer order;
  private String product;
  private String productVariant;
  private BigDecimal unitPrice;
  private Integer qty;
  private BigDecimal lineTotal;
}
