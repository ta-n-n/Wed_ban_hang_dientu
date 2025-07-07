package org.soft.elec.entity.dto.request.search;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVariantSearchRequest extends BaseSearchRequest {
  private Integer productId;
  private String keyword; // tìm theo tên, SKU
  private Boolean inStock;
  private Boolean isActive;
  private BigDecimal minPrice;
  private BigDecimal maxPrice;
  private LocalDateTime deletedBefore;
}
