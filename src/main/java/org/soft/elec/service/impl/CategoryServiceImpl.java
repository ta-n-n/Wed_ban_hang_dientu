package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.response.CategoryResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.CategoryMapper;
import org.soft.elec.entity.models.Category;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.CategoryRepository;
import org.soft.elec.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryRepository categoryRepository;

  @Autowired private CategoryMapper categoryMapper;

  private void checkCategoryExist(Integer id) {
    if (!categoryRepository.existsById(id)) {
      throw new AppEx(ErrorCode.CATEGORY_ALREADY_EXISTS);
    }
  }

  @Override
  @Transactional
  public CategoryResponse createCategory(CategoryRequest request) {
    Category category = categoryMapper.toEntity(request);
    Category saved = categoryRepository.save(category);
    return categoryMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public CategoryResponse updateCategory(Integer id, CategoryRequest request) {
    Category category =
        categoryRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.CATEGORY_NOT_FOUND));
    categoryMapper.updateEntity(request, category);
    Category updated = categoryRepository.save(category);
    return categoryMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteCategory(Integer id) {
    checkCategoryExist(id);
    categoryRepository.deleteById(id);
  }

  @Override
  public CategoryResponse getCategoryById(Integer id) {
    Category category =
        categoryRepository.findById(id).orElseThrow(() -> new AppEx(ErrorCode.CATEGORY_NOT_FOUND));
    return categoryMapper.toResponse(category);
  }

  @Override
  public List<CategoryResponse> getAllCategories() {
    return categoryRepository.findAll().stream()
        .map(categoryMapper::toResponse)
        .collect(Collectors.toList());
  }
}
