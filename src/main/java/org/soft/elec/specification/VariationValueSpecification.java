package org.soft.elec.specification;

import org.soft.elec.entity.models.VariationValue;
import org.springframework.data.jpa.domain.Specification;

public class VariationValueSpecification {

  public static Specification<VariationValue> hasLabel(String label) {
    return (root, query, cb) ->
        label == null || label.isBlank()
            ? null
            : cb.like(cb.lower(root.get("label")), "%" + label.toLowerCase() + "%");
  }

  public static Specification<VariationValue> hasValue(String value) {
    return (root, query, cb) ->
        value == null || value.isBlank()
            ? null
            : cb.like(cb.lower(root.get("value")), "%" + value.toLowerCase() + "%");
  }

  public static Specification<VariationValue> hasVariationId(Integer variationId) {
    return (root, query, cb) ->
        variationId == null ? null : cb.equal(root.get("variation").get("id"), variationId);
  }
}
