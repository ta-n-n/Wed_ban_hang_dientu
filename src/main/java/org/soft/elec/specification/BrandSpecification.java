package org.soft.elec.specification;

import org.soft.elec.entity.models.Brand;
import org.springframework.data.jpa.domain.Specification;

public class BrandSpecification {
  public static Specification<Brand> nameContains(String keyword) {
    return (root, query, cb) -> {
      if (keyword == null || keyword.isBlank()) return null;
      return cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
    };
  }

  public static Specification<Brand> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }
}
