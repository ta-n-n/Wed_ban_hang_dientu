package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileSearchRequest extends BaseSearchRequest {
  private Integer userId;
  private String filename;
  private String mime;
  private String extension;
}
