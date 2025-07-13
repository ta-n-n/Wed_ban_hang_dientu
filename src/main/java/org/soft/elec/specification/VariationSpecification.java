package org.soft.elec.specification;

import org.soft.elec.entity.models.Variation;
import org.springframework.data.jpa.domain.Specification;

public class VariationSpecification {
  private VariationSpecification() {}
  public static Specification<Variation> hasName(String name) {
    return (root, query, cb) ->
        name == null || name.isBlank()
            ? null
            : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }
  public static Specification<Variation> hasType(String type) {
    return (root, query, cb) ->
        type == null || type.isBlank()
            ? null
            : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
  }
  public static Specification<Variation> isGlobal(Boolean isGlobal) {
    return (root, query, cb) -> isGlobal == null ? null : cb.equal(root.get("isGlobal"), isGlobal);
  }
}
