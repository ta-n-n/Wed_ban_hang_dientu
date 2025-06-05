package org.soft.elec.repository;

import org.soft.elec.entity.models.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductVariantRepository
    extends JpaRepository<ProductVariant, Integer>, JpaSpecificationExecutor<ProductVariant> {}
