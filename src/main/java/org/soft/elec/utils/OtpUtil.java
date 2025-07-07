package org.soft.elec.utils;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class OtpUtil {
  private static final SecureRandom RANDOM = new SecureRandom();

  public String generate6DigitOtp() {
    int otp = 100000 + RANDOM.nextInt(900000);
    return String.valueOf(otp);
  }
}
