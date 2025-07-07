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
public class OptionValueResponse {
  private Integer id;
  private String option;
  private BigDecimal price;
  private String priceType;
  private Integer position;
}
