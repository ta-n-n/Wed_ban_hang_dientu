package org.soft.elec.entity.dto.request.search;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSearchRequest extends BaseSearchRequest {
  private String keyword; // Tìm theo tên, mô tả
  private Integer brandId;
  private Boolean isActive;
  private Boolean inStock; // Lọc theo trạng thái tồn kho
  private BigDecimal minPrice; // Giá thấp nhất
  private BigDecimal maxPrice; // Giá cao nhất
  private LocalDateTime newFrom;
  private LocalDateTime newTo;
}
