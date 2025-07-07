package org.soft.elec.service.impl;

import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.soft.elec.entity.models.PasswordResetOtp;
import org.soft.elec.entity.models.User;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.PasswordResetOtpRepository;
import org.soft.elec.repository.UserRepository;
import org.soft.elec.service.EmailService;
import org.soft.elec.service.PasswordResetService;
import org.soft.elec.utils.FullNameUtil;
import org.soft.elec.utils.OtpUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

  private final UserRepository userRepository;
  private final PasswordResetOtpRepository otpRepository;
  private final EmailService emailService;
  private final PasswordEncoder passwordEncoder;
  private final TemplateEngine templateEngine;
  private final OtpUtil otpUtil;
  private final FullNameUtil fullNameUtil;

  private static final int OTP_EXPIRY_MINUTES = 5;

  @Override
  public String initiatePasswordReset(String email) throws MessagingException {
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isEmpty()) {
      return "If an account with that email exists, an OTP has been sent to your email address.";
    }
    // Tạo và lưu OTP
    String otpCode = otpUtil.generate6DigitOtp();
    PasswordResetOtp otp = new PasswordResetOtp();
    otp.setOtp(otpCode);
    otp.setEmail(email);
    otp.setCreatedDate(LocalDateTime.now());
    otp.setExpiryDate(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
    otpRepository.save(otp);
    // Gửi email
    User user = optionalUser.get();
    String fullName = fullNameUtil.buildFullName(user);

    Context context = new Context();
    context.setVariable("fullName", fullName);
    context.setVariable("otpCode", otpCode);
    String htmlBody = templateEngine.process("password-reset", context);
    emailService.sendHtmlEmail(email, "Password Reset OTP", htmlBody);
    return "If an account with that email exists, an OTP has been sent to your email address.";
  }

  @Override
  public void resetPassword(String email, String otpCode, String newPassword) {
    PasswordResetOtp passwordResetOtp =
        otpRepository
            .findByEmailAndOtp(email, otpCode)
            .orElseThrow(() -> new AppException(ErrorCode.INVALID_OTP));
    if (passwordResetOtp.getExpiryDate().isBefore(LocalDateTime.now())) {
      otpRepository.delete(passwordResetOtp);
      throw new AppException(ErrorCode.OTP_EXPIRED);
    }
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
    otpRepository.delete(passwordResetOtp);
  }
}
