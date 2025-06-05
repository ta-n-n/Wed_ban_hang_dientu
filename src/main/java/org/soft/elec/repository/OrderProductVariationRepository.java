package org.soft.elec.repository;

import org.soft.elec.entity.models.OrderProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderProductVariationRepository extends JpaRepository<OrderProductVariation, Integer>, JpaSpecificationExecutor<OrderProductVariation> {
}
