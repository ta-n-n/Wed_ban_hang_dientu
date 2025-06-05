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
public class FileRequest {
  @NotNull(message = "{file.userid.notnull}")
  private Integer userId;

  @NotBlank(message = "{file.filename.notblank}")
  private String filename;

  @NotBlank(message = "{file.disk.notblank}")
  private String disk;

  @NotBlank(message = "{file.path.notblank}")
  private String path;

  @NotBlank(message = "{file.extension.notblank}")
  private String extension;

  @NotBlank(message = "{file.mime.notblank}")
  private String mime;

  @NotBlank(message = "{file.size.notblank}")
  private String size;
}
