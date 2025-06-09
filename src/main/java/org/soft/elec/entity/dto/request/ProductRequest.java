package org.soft.elec.entity.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.ProductVariant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

  @NotNull(message = "Product name is required")
  private String name; // Tên sản phẩm

  private String thumbnail; // Hình ảnh thu nhỏ của sản phẩm

  @NotNull(message = "Brand is required")
  private Integer brandId; // ID thương hiệu

  private String description; // Mô tả chi tiết sản phẩm

  private String shortDescription; // Mô tả ngắn gọn

  @NotNull(message = "Price is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  private BigDecimal price; // Giá gốc

  private BigDecimal specialPrice; // Giá khuyến mãi

  private Integer specialPriceType; // Loại giá khuyến mãi: 1 - Fixed, 2 - Percent

  private LocalDateTime specialPriceStart; // Thời gian bắt đầu khuyến mãi

  private LocalDateTime specialPriceEnd; // Thời gian kết thúc khuyến mãi

  private BigDecimal sellingPrice; // Giá bán sau khi tính khuyến mãi

  private String sku; // Mã sản phẩm

  @NotNull(message = "Stock management status is required")
  private Boolean manageStock; // Quản lý tồn kho (true - quản lý, false - không)

  @Min(value = 0, message = "Quantity must be greater than or equal to 0")
  private Integer qty; // Số lượng trong kho

  @NotNull(message = "Stock availability status is required")
  private Boolean inStock; // Trạng thái tồn kho (true - còn hàng, false - hết hàng)

  private Boolean isActive; // Trạng thái hoạt động của sản phẩm (true - hoạt động, false - ngừng)

  private LocalDateTime newFrom; // Thời gian bắt đầu sản phẩm mới

  private LocalDateTime newTo; // Thời gian kết thúc sản phẩm mới

  private LocalDateTime deletedAt; // Thời gian sản phẩm bị xóa

  private List<Integer> categoryIds; // Danh sách ID các danh mục sản phẩm thuộc về

  private List<Integer> variationIds; // Danh sách ID các biến thể của sản phẩm

  private List<Integer> optionIds; // Danh sách ID các tùy chọn cho sản phẩm

  private List<ProductVariant> variantIds; // (size, color, v.v.)
}
