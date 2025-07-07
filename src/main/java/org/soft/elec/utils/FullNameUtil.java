package org.soft.elec.utils;

import org.soft.elec.entity.models.User;
import org.springframework.stereotype.Component;

@Component
public class FullNameUtil {
  public String buildFullName(User user) {
    if (user == null) return "";
    String first = user.getFirstName() != null ? user.getFirstName() : "";
    String last = user.getLastName() != null ? user.getLastName() : "";
    return (first + " " + last).trim();
  }
}
