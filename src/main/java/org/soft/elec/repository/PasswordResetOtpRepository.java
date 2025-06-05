package org.soft.elec.repository;

import java.util.Optional;
import org.soft.elec.entity.models.PasswordResetOtp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetOtpRepository extends JpaRepository<PasswordResetOtp, String> {
  Optional<PasswordResetOtp> findByEmailAndOtp(String email, String otp);
}
