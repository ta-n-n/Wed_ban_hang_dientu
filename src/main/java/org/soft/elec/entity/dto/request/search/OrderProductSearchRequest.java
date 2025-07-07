package org.soft.elec.entity.dto.request.search;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderProductSearchRequest extends BaseSearchRequest {
  private Integer orderId;
  private Integer productId;
  private Integer productVariantId;
  private BigDecimal minLineTotal;
  private BigDecimal maxLineTotal;
}
