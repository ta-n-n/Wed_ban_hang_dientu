package org.soft.elec.specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.soft.elec.entity.models.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
  private OrderSpecification() {}
  public static Specification<Order> hasCustomerEmail(String email) {
    return (root, query, cb) ->
        email == null || email.isBlank()
            ? null
            : cb.like(cb.lower(root.get("customerEmail")), "%" + email.toLowerCase() + "%");
  }
  public static Specification<Order> hasCustomerPhone(String phone) {
    return (root, query, cb) ->
        phone == null || phone.isBlank()
            ? null
            : cb.like(cb.lower(root.get("customerPhone")), "%" + phone.toLowerCase() + "%");
  }
  public static Specification<Order> hasStatus(String status) {
    return (root, query, cb) ->
        status == null || status.isBlank() ? null : cb.equal(root.get("status"), status);
  }
  public static Specification<Order> createdAfter(LocalDateTime from) {
    return (root, query, cb) ->
        from == null ? null : cb.greaterThanOrEqualTo(root.get("createdAt"), from);
  }
  public static Specification<Order> createdBefore(LocalDateTime to) {
    return (root, query, cb) -> to == null ? null : cb.lessThanOrEqualTo(root.get("createdAt"), to);
  }
  public static Specification<Order> totalGreaterThan(BigDecimal min) {
    return (root, query, cb) ->
        min == null ? null : cb.greaterThanOrEqualTo(root.get("total"), min);
  }
  public static Specification<Order> totalLessThan(BigDecimal max) {
    return (root, query, cb) -> max == null ? null : cb.lessThanOrEqualTo(root.get("total"), max);
  }
}
