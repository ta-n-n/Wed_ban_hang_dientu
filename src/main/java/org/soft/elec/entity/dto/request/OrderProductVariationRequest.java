package org.soft.elec.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductVariationRequest {

  @NotNull(message = "Order Product ID is required")
  private Integer orderProductId;

  @NotNull(message = "Variation ID is required")
  private Integer variationId;

  @NotNull(message = "Type is required")
  @Size(min = 1, max = 50, message = "Type must be between 1 and 50 characters")
  private String type;

  @NotNull(message = "Value is required")
  @Size(min = 1, max = 255, message = "Value must be between 1 and 255 characters")
  private String value;
}
