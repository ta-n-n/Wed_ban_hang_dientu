package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.Product;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantResponse {
    private Integer id;
    private String name;
    private Product product;
    private BigDecimal price;
    private BigDecimal specialPrice;
    private Integer specialPriceType;
    private Date specialPriceStart;
    private Date specialPriceEnd;
    private BigDecimal sellingPrice;
    private String sku;
    private Boolean manageStock;
    private Integer qty;
    private Boolean inStock;
    private Boolean isActive;
    private Integer position;
}
