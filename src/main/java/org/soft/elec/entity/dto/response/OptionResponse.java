package org.soft.elec.entity.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.OptionValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionResponse {
  private Integer id;
  private String type;
  private Boolean isRequired;
  private Boolean isGlobal;
  private Integer position;
  private List<OptionValue> values;
}
