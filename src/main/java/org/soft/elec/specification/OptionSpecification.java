package org.soft.elec.specification;

import org.soft.elec.entity.models.Option;
import org.springframework.data.jpa.domain.Specification;

public class OptionSpecification {
  private OptionSpecification() {}
  public static Specification<Option> hasType(String type) {
    return (root, query, cb) ->
        type == null || type.isBlank()
            ? null
            : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
  }
  public static Specification<Option> isRequired(Boolean required) {
    return (root, query, cb) ->
        required == null ? null : cb.equal(root.get("isRequired"), required);
  }
  public static Specification<Option> isGlobal(Boolean global) {
    return (root, query, cb) -> global == null ? null : cb.equal(root.get("isGlobal"), global);
  }
}
