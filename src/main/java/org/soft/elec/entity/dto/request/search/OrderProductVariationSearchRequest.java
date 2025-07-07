package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderProductVariationSearchRequest extends BaseSearchRequest {
  private Integer orderProductId;
  private Integer variationId;
  private String type;
  private String value;
}
