package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariationValueResponse {
  private Integer id;
  private String label;
  private Integer variationId;
  private String variationName;
  private String value;
  private Integer position;
}
