package org.soft.elec.repository;

import org.soft.elec.entity.models.OrderProductVariationValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderProductVariationValueRepository
    extends JpaRepository<OrderProductVariationValue, Integer>,
        JpaSpecificationExecutor<OrderProductVariationValue> {}
