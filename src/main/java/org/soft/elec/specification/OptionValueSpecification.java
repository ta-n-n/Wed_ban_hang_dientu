package org.soft.elec.specification;

import java.math.BigDecimal;
import org.soft.elec.entity.models.OptionValue;
import org.springframework.data.jpa.domain.Specification;

public class OptionValueSpecification {

  public static Specification<OptionValue> hasOptionId(Integer optionId) {
    return (root, query, cb) ->
        optionId == null ? null : cb.equal(root.get("option").get("id"), optionId);
  }

  public static Specification<OptionValue> hasPriceType(String priceType) {
    return (root, query, cb) ->
        priceType == null || priceType.isBlank()
            ? null
            : cb.equal(cb.lower(root.get("priceType")), priceType.toLowerCase());
  }

  public static Specification<OptionValue> hasMinPrice(BigDecimal minPrice) {
    return (root, query, cb) ->
        minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
  }

  public static Specification<OptionValue> hasMaxPrice(BigDecimal maxPrice) {
    return (root, query, cb) ->
        maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
  }
}
