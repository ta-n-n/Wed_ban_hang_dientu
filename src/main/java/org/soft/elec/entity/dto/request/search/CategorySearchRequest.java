package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategorySearchRequest extends BaseSearchRequest {
  private String keyword;
  private Boolean isActive;
  private Integer parentId;
}
