package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.enums.Role;

import java.time.LocalDateTime;

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
