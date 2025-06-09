package org.soft.elec.entity.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
  private Integer id;
  private String thumbnail;
  private String brand;
  private String name;
  private String description;
  private String shortDescription;
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
  private LocalDateTime newFrom;
  private LocalDateTime newTo;
  private List<String> categories;
  private List<String> variations;
  private List<String> options;
  private List<String> variants;
}
