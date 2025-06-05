package org.soft.elec.repository;

import org.soft.elec.entity.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository
    extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {}
