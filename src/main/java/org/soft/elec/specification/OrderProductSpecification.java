package org.soft.elec.specification;

import java.math.BigDecimal;
import org.soft.elec.entity.models.OrderProduct;
import org.springframework.data.jpa.domain.Specification;

public class OrderProductSpecification {
  private OrderProductSpecification() {}
  public static Specification<OrderProduct> hasOrderId(Integer orderId) {
    return (root, query, cb) ->
        orderId == null ? null : cb.equal(root.get("order").get("id"), orderId);
  }
  public static Specification<OrderProduct> hasProductId(Integer productId) {
    return (root, query, cb) ->
        productId == null ? null : cb.equal(root.get("product").get("id"), productId);
  }
  public static Specification<OrderProduct> hasProductVariantId(Integer variantId) {
    return (root, query, cb) ->
        variantId == null ? null : cb.equal(root.get("productVariant").get("id"), variantId);
  }
  public static Specification<OrderProduct> hasMinLineTotal(BigDecimal min) {
    return (root, query, cb) ->
        min == null ? null : cb.greaterThanOrEqualTo(root.get("lineTotal"), min);
  }
  public static Specification<OrderProduct> hasMaxLineTotal(BigDecimal max) {
    return (root, query, cb) ->
        max == null ? null : cb.lessThanOrEqualTo(root.get("lineTotal"), max);
  }
}
