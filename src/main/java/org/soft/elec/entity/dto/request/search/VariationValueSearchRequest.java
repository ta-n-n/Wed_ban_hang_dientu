package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VariationValueSearchRequest extends BaseSearchRequest {
  private String label;
  private String value;
  private Integer variationId;
}
