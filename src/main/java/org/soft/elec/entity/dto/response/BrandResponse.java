package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.Product;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private Integer id;

    private String name;

    private Boolean isActive; //0:Disable　1: Enable

    private String fileLogo;

    private List<Product> products;
}
