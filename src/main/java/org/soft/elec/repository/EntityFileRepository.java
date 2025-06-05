package org.soft.elec.repository;

import org.soft.elec.entity.models.EntityFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EntityFileRepository
    extends JpaRepository<EntityFile, Integer>, JpaSpecificationExecutor<EntityFile> {}
