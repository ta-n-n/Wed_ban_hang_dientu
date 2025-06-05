package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.response.CategoryResponse;

public interface CategoryService {
  CategoryResponse createCategory(CategoryRequest request);

  CategoryResponse updateCategory(Integer id, CategoryRequest request);

  void deleteCategory(Integer id);

  CategoryResponse getCategoryById(Integer id);

  List<CategoryResponse> getAllCategories();
}
