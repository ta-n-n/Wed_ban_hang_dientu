package org.soft.elec.entity.dto.request.search;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soft.elec.constant.enums.Role;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserSearchRequest extends BaseSearchRequest {
  private String keyword; // tìm theo tên, email, phone
  private Role role;
  private Boolean enabled;
}
