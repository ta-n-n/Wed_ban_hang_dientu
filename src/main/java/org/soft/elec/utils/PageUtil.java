package org.soft.elec.utils;

import org.soft.elec.entity.dto.request.search.BaseSearchRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {
  private PageUtil() {}
  public static Pageable getPageable(BaseSearchRequest request) {
    return PageRequest.of(
        request.getPage(), request.getSize(), Sort.by(request.getDirection(), request.getSortBy()));
  }
}
