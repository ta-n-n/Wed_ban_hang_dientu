package org.soft.elec.repository;

import org.soft.elec.entity.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OptionRepository extends JpaRepository<Option, Integer>, JpaSpecificationExecutor<Option> {
}
