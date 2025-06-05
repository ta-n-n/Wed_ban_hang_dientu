package org.soft.elec.entity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "password_reset_otp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetOtp {
  @Id private String otp;

  private String email;
  private LocalDateTime expiryDate;

  @CreationTimestamp private LocalDateTime createdDate;
}
