package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public abstract class BaseSearchRequest {
  private int page = 0;
  private int size = 10;
  private String sortBy = "createdAt";
  private Sort.Direction direction = Sort.Direction.DESC;
}
