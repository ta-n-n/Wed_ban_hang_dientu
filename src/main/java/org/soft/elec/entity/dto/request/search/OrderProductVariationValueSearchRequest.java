package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderProductVariationValueSearchRequest extends BaseSearchRequest {
  private Integer orderProductVariationId;
  private Integer variationValueId;
}
