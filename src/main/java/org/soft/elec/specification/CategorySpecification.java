package org.soft.elec.specification;

import org.soft.elec.entity.models.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

  public static Specification<Category> nameContains(String keyword) {
    return (root, query, cb) ->
        (keyword == null || keyword.isBlank())
            ? null
            : cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
  }

  public static Specification<Category> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }

  public static Specification<Category> hasParentId(Integer parentId) {
    return (root, query, cb) ->
        parentId == null ? null : cb.equal(root.get("parent").get("id"), parentId);
  }
}
