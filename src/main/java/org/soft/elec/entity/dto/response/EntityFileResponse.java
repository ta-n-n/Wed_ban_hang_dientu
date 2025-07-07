package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityFileResponse {
  private Integer id;
  private Integer fileId;
  private String fileName;
  private Integer entityId;
  private String entityType;
  private String zone;
}
