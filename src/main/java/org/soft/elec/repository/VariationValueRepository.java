package org.soft.elec.repository;

import org.soft.elec.entity.models.VariationValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VariationValueRepository extends JpaRepository<VariationValue, Integer>, JpaSpecificationExecutor<VariationValue> {
}
