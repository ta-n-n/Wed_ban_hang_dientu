package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.Order;
import org.soft.elec.entity.models.Product;
import org.soft.elec.entity.models.ProductVariant;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductResponse {
    private Integer id;
    private Order order;
    private Product product;
    private ProductVariant productVariant;
    private BigDecimal unitPrice;
    private Integer qty = 1;
    private BigDecimal lineTotal;
}
