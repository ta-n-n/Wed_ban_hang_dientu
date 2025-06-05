package org.soft.elec.service;

import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Integer id, CategoryRequest request);
    void deleteCategory(Integer id);
    CategoryResponse getCategoryById(Integer id);
    List<CategoryResponse> getAllCategories();
}
