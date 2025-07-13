package org.soft.elec.specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.soft.elec.entity.models.ProductVariant;
import org.springframework.data.jpa.domain.Specification;

public class ProductVariantSpecification {
  private static final String PRICE_FIELD = "price";
  private ProductVariantSpecification() {}
  public static Specification<ProductVariant> hasProductId(Integer productId) {
    return (root, query, cb) ->
        productId == null ? null : cb.equal(root.get("product").get("id"), productId);
  }
  public static Specification<ProductVariant> keywordContains(String keyword) {
    return (root, query, cb) ->
        keyword == null || keyword.isBlank()
            ? null
            : cb.or(
                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("sku")), "%" + keyword.toLowerCase() + "%"));
  }
  public static Specification<ProductVariant> isInStock(Boolean inStock) {
    return (root, query, cb) -> inStock == null ? null : cb.equal(root.get("inStock"), inStock);
  }
  public static Specification<ProductVariant> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }
  public static Specification<ProductVariant> priceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> {
      if (min == null && max == null) return null;
      if (min != null && max != null) return cb.between(root.get(PRICE_FIELD), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get(PRICE_FIELD), min);
      return cb.lessThanOrEqualTo(root.get(PRICE_FIELD), max);
    };
  }
  public static Specification<ProductVariant> deletedBefore(LocalDateTime deletedAt) {
    return (root, query, cb) ->
        deletedAt == null ? null : cb.lessThanOrEqualTo(root.get("deletedAt"), deletedAt);
  }
}
