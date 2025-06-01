package org.soft.elec.entity.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
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

    @Column(name = "thumbnail")
    private String thumbnail; // Hình ảnh thu nhỏ

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand; // Thương hiệu

    @Column(name = "name", nullable = false)
    private String name; // Tên

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description; // Mô tả chi tiết

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription; // Mô tả ngắn gọn

    @Column(name = "price", precision = 18, scale = 4, nullable = false)
    private BigDecimal price; // Giá gốc

    @Column(name = "special_price", precision = 18, scale = 4)
    private BigDecimal specialPrice; // Giá khuyến mãi

    @Column(name = "special_price_type")
    private Integer specialPriceType; // Loại giá khuyến mãi: 1: Fixed, 2: Percent

    @Column(name = "special_price_start")
    private Date specialPriceStart; // Bắt đầu khuyến mãi

    @Column(name = "special_price_end")
    private Date specialPriceEnd; // Kết thúc khuyến mãi

    @Column(name = "selling_price", precision = 18, scale = 4)
    // Cách tính sellingPrice: percent = price - (special_price / 100) * price, fixed = price - special_price
    private BigDecimal sellingPrice; // Giá bán sau tính khuyến mãi

    @Column(name = "sku")
    private String sku; // Mã sản phẩm (SKU)

    @Column(name = "manage_stock")
    private Boolean manageStock; // Quản lý tồn kho: 0 - Không theo dõi, 1 - Theo dõi

    @Column(name = "qty")
    private Integer qty; // Số lượng trong kho

    @Column(name = "in_stock")
    private Boolean inStock; // Trạng thái tồn kho: 1 - Còn hàng, 0 - Hết hàng

    @Column(name = "is_active")
    private Boolean isActive; // 1 - Hoạt động, 0 - Ngừng hoạt động

    @Column(name = "new_from")
    private LocalDateTime newFrom;

    @Column(name = "new_to")
    private LocalDateTime newTo;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories; // Các danh mục sản phẩm thuộc về

    @ManyToMany
    @JoinTable(
            name = "product_variations",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "variation_id")
    )
    private List<Variation> variations; // Các biến thể (variations) của sản phẩm

    @ManyToMany
    @JoinTable(
            name = "product_options",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> options; // Các tùy chọn (options) cho sản phẩm

    @OneToMany(mappedBy = "product")
    private List<ProductVariant> variants; // Các biến thể của sản phẩm (size, color, v.v.)

}
