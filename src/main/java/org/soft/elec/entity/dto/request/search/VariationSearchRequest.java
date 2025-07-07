package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VariationSearchRequest extends BaseSearchRequest {
  private String name;
  private String type;
  private Boolean isGlobal;
}
