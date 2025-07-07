package org.soft.elec.specification;

import org.soft.elec.entity.models.OrderProductVariation;
import org.springframework.data.jpa.domain.Specification;

public class OrderProductVariationSpecification {

  public static Specification<OrderProductVariation> hasOrderProductId(Integer id) {
    return (root, query, cb) ->
        id == null ? null : cb.equal(root.get("orderProduct").get("id"), id);
  }

  public static Specification<OrderProductVariation> hasVariationId(Integer id) {
    return (root, query, cb) -> id == null ? null : cb.equal(root.get("variation").get("id"), id);
  }

  public static Specification<OrderProductVariation> hasType(String type) {
    return (root, query, cb) ->
        (type == null || type.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
  }

  public static Specification<OrderProductVariation> hasValue(String value) {
    return (root, query, cb) ->
        (value == null || value.isBlank())
            ? null
            : cb.like(cb.lower(root.get("value")), "%" + value.toLowerCase() + "%");
  }
}
