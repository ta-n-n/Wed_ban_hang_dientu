package org.soft.elec.entity.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.constant.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private boolean enabled;
  private Role role;
  private LocalDateTime lastLogin;
}
