package org.soft.elec.entity.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uid;

    private String uids;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;

    @Column(precision = 18, scale = 4)
    private BigDecimal price;

    @Column(name = "special_price", precision = 18, scale = 4)
    private BigDecimal specialPrice;

    @Column(name = "special_price_type")
    private String specialPriceType;

    @Column(name = "special_price_start")
    private LocalDateTime specialPriceStart;

    @Column(name = "special_price_end")
    private LocalDateTime specialPriceEnd;

    @Column(name = "selling_price", precision = 18, scale = 4)
    private BigDecimal sellingPrice;

    private String sku;

    @Column(name = "manage_stock")
    private Boolean manageStock;

    private Integer qty;

    @Column(name = "in_stock")
    private Boolean inStock;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "is_active")
    private Boolean isActive;

    private Integer position;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
