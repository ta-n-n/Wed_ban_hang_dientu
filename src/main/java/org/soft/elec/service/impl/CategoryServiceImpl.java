package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.CategoryRequest;
import org.soft.elec.entity.dto.request.search.CategorySearchRequest;
import org.soft.elec.entity.dto.response.CategoryResponse;
import org.soft.elec.entity.mapper.CategoryMapper;
import org.soft.elec.entity.models.Category;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.CategoryRepository;
import org.soft.elec.service.CategoryService;
import org.soft.elec.specification.CategorySpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Override
  @Transactional
  public CategoryResponse createCategory(CategoryRequest request) {
    Category parentCategory = null;
    if (request.getParentId() != null) {
      parentCategory =
          categoryRepository
              .findById(request.getParentId())
              .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }
    Category category = categoryMapper.toEntity(request);
    category.setParent(parentCategory);
    Category saved = categoryRepository.save(category);
    return categoryMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public CategoryResponse updateCategory(Integer id, CategoryRequest request) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    categoryMapper.updateEntity(request, category);
    if (request.getParentId() != null) {
      Category parent =
          categoryRepository
              .findById(request.getParentId())
              .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
      category.setParent(parent);
    } else {
      category.setParent(null);
    }
    Category updated = categoryRepository.save(category);
    return categoryMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteCategory(Integer id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    categoryRepository.delete(category);
  }

  @Override
  public CategoryResponse getCategoryById(Integer id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    return categoryMapper.toResponse(category);
  }

  @Override
  public List<CategoryResponse> getAllCategories() {
    return categoryRepository.findAll().stream()
        .map(categoryMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<CategoryResponse> searchCategories(CategorySearchRequest request) {
    Specification<Category> spec = null;

    spec = SpecUtil.add(spec, CategorySpecification.nameContains(request.getKeyword()));
    spec = SpecUtil.add(spec, CategorySpecification.isActive(request.getIsActive()));
    spec = SpecUtil.add(spec, CategorySpecification.hasParentId(request.getParentId()));

    Pageable pageable = PageUtil.getPageable(request);

    return categoryRepository.findAll(spec, pageable).map(categoryMapper::toResponse);
  }
}
