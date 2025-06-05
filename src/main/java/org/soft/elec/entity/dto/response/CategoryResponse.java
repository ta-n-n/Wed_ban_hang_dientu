package org.soft.elec.entity.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.Category;
import org.soft.elec.entity.models.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
  private Integer id;
  private Category parent;
  private List<Category> children;
  private String name;
  private Integer position;
  private Boolean isActive;
  private List<Product> products;
}
