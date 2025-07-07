package org.soft.elec.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

  private final PasswordEncoder encoder = new BCryptPasswordEncoder(10);

  public boolean matches(String raw, String encoded) {
    return encoder.matches(raw, encoded);
  }

  public String encode(String raw) {
    return encoder.encode(raw);
  }
}
