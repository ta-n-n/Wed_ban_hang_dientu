package org.soft.elec.repository;

import org.soft.elec.entity.models.PasswordResetOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PasswordResetOtpRepository extends JpaRepository<PasswordResetOtp, String> {
    Optional<PasswordResetOtp> findByEmailAndOtp(String email, String otp);
}
