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
public class CategoryRequest {

  private Integer parentId; // Cho phép null nếu là danh mục gốcs

  @NotBlank(message = "{category.name.notblank}")
  private String name;

  private Integer position;

  @NotNull(message = "{category.isactive.notnull}")
  private Boolean isActive;
}
