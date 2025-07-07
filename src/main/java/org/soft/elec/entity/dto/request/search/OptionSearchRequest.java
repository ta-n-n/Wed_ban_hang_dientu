package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OptionSearchRequest extends BaseSearchRequest {
  private String type;
  private Boolean isRequired;
  private Boolean isGlobal;
}
