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
public class BrandRequest {

  @NotBlank(message = "{brandname.notblank}")
  private String name;

  @NotNull(message = "{brand.active}")
  private Boolean isActive; // 0:Disable　1: Enable

  private String fileLogo;
}
