package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EntityFileSearchRequest extends BaseSearchRequest {
  private Integer entityId;
  private String entityType;
  private String zone;
}
