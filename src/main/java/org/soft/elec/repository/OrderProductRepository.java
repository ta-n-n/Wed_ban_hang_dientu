package org.soft.elec.repository;

import org.soft.elec.entity.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer>, JpaSpecificationExecutor<OrderProduct> {
}
