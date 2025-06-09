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
public class VariationResponse {
  private Integer id;
  private String name;
  private String type;
  private Boolean isGlobal;
  private Integer position;
  private List<String> products;
  private List<String> variationValues;
}
