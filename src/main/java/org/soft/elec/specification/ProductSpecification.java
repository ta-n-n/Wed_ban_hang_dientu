package org.soft.elec.specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.soft.elec.entity.models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

  public static Specification<Product> keywordContains(String keyword) {
    return (root, query, cb) ->
        keyword == null || keyword.isBlank()
            ? null
            : cb.or(
                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%"));
  }

  public static Specification<Product> hasBrand(Integer brandId) {
    return (root, query, cb) ->
        brandId == null ? null : cb.equal(root.get("brand").get("id"), brandId);
  }

  public static Specification<Product> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }

  public static Specification<Product> inStock(Boolean inStock) {
    return (root, query, cb) -> inStock == null ? null : cb.equal(root.get("inStock"), inStock);
  }

  public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> {
      if (min == null && max == null) return null;
      if (min != null && max != null) return cb.between(root.get("price"), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
      return cb.lessThanOrEqualTo(root.get("price"), max);
    };
  }

  public static Specification<Product> newDateBetween(LocalDateTime from, LocalDateTime to) {
    return (root, query, cb) -> {
      if (from == null && to == null) return null;
      if (from != null && to != null) return cb.between(root.get("newFrom"), from, to);
      if (from != null) return cb.greaterThanOrEqualTo(root.get("newFrom"), from);
      return cb.lessThanOrEqualTo(root.get("newTo"), to);
    };
  }
}
