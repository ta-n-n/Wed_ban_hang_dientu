package org.soft.elec.entity.dto.request.search;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OptionValueSearchRequest extends BaseSearchRequest {
  private Integer optionId;
  private String priceType;
  private BigDecimal minPrice;
  private BigDecimal maxPrice;
}
