package org.soft.elec.entity.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "tax_class_id")
    private Integer taxClassId;

    private String slug;

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

    private Integer viewed;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "new_from")
    private LocalDateTime newFrom;

    @Column(name = "new_to")
    private LocalDateTime newTo;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "product_variations",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "variation_id")
    )
    private List<Variation> variations;

    @ManyToMany
    @JoinTable(
            name = "product_options",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> options;

    @OneToMany(mappedBy = "product")
    private List<ProductVariant> variants;
}

