package org.soft.elec.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.dto.response.CategoryResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    private Integer parentId; // Cho phép null nếu là danh mục gốc

    private List<CategoryResponse> children;

    @NotBlank(message = "{category.name.notblank}")
    private String name;

    private Integer position;

    @NotNull(message = "{category.isactive.notnull}")
    private Boolean isActive;
}
