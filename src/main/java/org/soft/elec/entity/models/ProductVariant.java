package org.soft.elec.entity.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product_variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "price", precision = 18, scale = 4)
  private BigDecimal price;

  @Column(name = "special_price", precision = 18, scale = 4)
  private BigDecimal specialPrice; // Giá khuyến mãi

  @Column(name = "special_price_type")
  private Integer specialPriceType; // Loại giá khuyến mãi: 1: Fixed, 2: Percent

  @Column(name = "special_price_start")
  private Date specialPriceStart; // Bắt đầu khuyến mãi

  @Column(name = "special_price_end")
  private Date specialPriceEnd; // Kết thúc khuyến mãi

  @Column(name = "selling_price", precision = 18, scale = 4)
  // Cách tính sellingPrice: percent = price - (special_price / 100) * price, fixed = price -
  // special_price
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

  @Column(name = "position")
  private Integer position;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
