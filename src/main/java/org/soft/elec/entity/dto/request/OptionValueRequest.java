package org.soft.elec.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionValueRequest {

    @NotNull(message = "{optionvalue.optionid.notnull}")
    private Integer optionId;

    private BigDecimal price;

    private String priceType;

    private Integer position;
}
