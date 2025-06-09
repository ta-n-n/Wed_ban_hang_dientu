package org.soft.elec.entity.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
  private Integer id;
  private String parent;
  private List<String> children;
  private String name;
  private Integer position;
  private Boolean isActive;
}
