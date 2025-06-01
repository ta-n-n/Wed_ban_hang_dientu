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
public class OptionRequest {

    @NotBlank(message = "{option.type.notblank}")
    private String type;

    @NotNull(message = "{option.isrequired.notnull}")
    private Boolean isRequired;

    @NotNull(message = "{option.isglobal.notnull}")
    private Boolean isGlobal;

    private Integer position;
}
