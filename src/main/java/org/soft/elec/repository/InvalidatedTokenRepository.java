package org.soft.elec.repository;

import org.soft.elec.entity.models.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
    boolean existsByToken(String token);
}
