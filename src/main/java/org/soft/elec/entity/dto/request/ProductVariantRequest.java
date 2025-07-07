package org.soft.elec.entity.dto.request;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantRequest {
  private String name;
  private Integer productId;
  private BigDecimal price;
  private BigDecimal specialPrice;
  private Integer specialPriceType;
  private Date specialPriceStart;
  private Date specialPriceEnd;
  private BigDecimal sellingPrice;
  private String sku;
  private Boolean manageStock;
  private Integer qty;
  private Boolean inStock;
  private Boolean isActive;
  private Integer position;
}
