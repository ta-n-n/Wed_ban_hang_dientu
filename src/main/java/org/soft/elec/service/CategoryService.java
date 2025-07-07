package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.request.search.CategorySearchRequest;
import org.soft.elec.entity.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;

public interface CategoryService {
  CategoryResponse createCategory(CategoryRequest request);

  CategoryResponse updateCategory(Integer id, CategoryRequest request);

  void deleteCategory(Integer id);

  CategoryResponse getCategoryById(Integer id);

  List<CategoryResponse> getAllCategories();

  Page<CategoryResponse> searchCategories(CategorySearchRequest request);
}
