package org.soft.elec.utils;

import org.springframework.data.jpa.domain.Specification;

public class SpecUtil {
  public static <T> Specification<T> add(Specification<T> base, Specification<T> addition) {
    if (base == null) return addition;
    if (addition == null) return base;
    return base.and(addition);
  }
}
