package org.soft.elec.repository;

import java.util.Optional;
import org.soft.elec.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository
    extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
  Optional<User> findByEmail(String email);

  String email(String email);
}
