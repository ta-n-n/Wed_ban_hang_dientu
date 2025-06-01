package org.soft.elec.entity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(
        name = "order_product_variation_values",
        uniqueConstraints = @UniqueConstraint(
                name = "opv_variation_unique",
                columnNames = {"order_product_variation_id", "variation_value_id"}
        )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductVariationValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_product_variation_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderProductVariation orderProductVariation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variation_value_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VariationValue variationValue;
}
