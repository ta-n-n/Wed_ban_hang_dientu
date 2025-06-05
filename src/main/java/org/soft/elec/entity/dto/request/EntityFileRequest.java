package org.soft.elec.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityFileRequest {

  @NotNull(message = "{entityfile.fileid.notnull}")
  private Integer fileId;

  @NotNull(message = "{entityfile.entityid.notnull}")
  private Integer entityId;

  @NotBlank(message = "{entityfile.entitytype.notblank}")
  private String entityType;

  @NotBlank(message = "{entityfile.zone.notblank}")
  private String zone;
}
