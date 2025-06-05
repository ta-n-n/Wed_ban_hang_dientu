package org.soft.elec.entity.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.Product;
import org.soft.elec.entity.models.VariationValue;

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
  private List<Product> products;
  private List<VariationValue> variationValues;
}
