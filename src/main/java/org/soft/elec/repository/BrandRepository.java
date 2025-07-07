package org.soft.elec.repository;

import org.soft.elec.entity.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BrandRepository
    extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {}
