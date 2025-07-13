package org.soft.elec.specification;

import org.soft.elec.constant.enums.Role;
import org.soft.elec.entity.models.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
  private UserSpecification() {}
  public static Specification<User> keywordContains(String keyword) {
    return (root, query, cb) ->
        keyword == null || keyword.isBlank()
            ? null
            : cb.or(
                cb.like(cb.lower(root.get("firstName")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("lastName")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("email")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("phone")), "%" + keyword.toLowerCase() + "%"));
  }
  public static Specification<User> hasRole(Role role) {
    return (root, query, cb) -> role == null ? null : cb.equal(root.get("role"), role);
  }
  public static Specification<User> isEnabled(Boolean enabled) {
    return (root, query, cb) -> enabled == null ? null : cb.equal(root.get("enabled"), enabled);
  }
}
