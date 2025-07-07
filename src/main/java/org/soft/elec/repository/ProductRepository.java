package org.soft.elec.repository;

import org.soft.elec.entity.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository
    extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {}
