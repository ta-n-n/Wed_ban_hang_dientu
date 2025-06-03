package org.soft.elec.repository;

import org.soft.elec.entity.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FileRepository extends JpaRepository<File, Integer>, JpaSpecificationExecutor<File> {
}
