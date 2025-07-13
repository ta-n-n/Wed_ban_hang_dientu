package org.soft.elec.specification;

import org.soft.elec.entity.models.OrderProductVariationValue;
import org.springframework.data.jpa.domain.Specification;

public class OrderProductVariationValueSpecification {
  private OrderProductVariationValueSpecification() {}
  public static Specification<OrderProductVariationValue> hasOrderProductVariationId(Integer id) {
    return (root, query, cb) ->
        id == null ? null : cb.equal(root.get("orderProductVariation").get("id"), id);
  }
  public static Specification<OrderProductVariationValue> hasVariationValueId(Integer id) {
    return (root, query, cb) ->
        id == null ? null : cb.equal(root.get("variationValue").get("id"), id);
  }
}
