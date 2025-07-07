package org.soft.elec.entity.dto.request.search;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderSearchRequest extends BaseSearchRequest {
  private String customerEmail;
  private String customerPhone;
  private String status;
  private LocalDateTime fromCreatedAt;
  private LocalDateTime toCreatedAt;
  private BigDecimal minTotal;
  private BigDecimal maxTotal;
}
