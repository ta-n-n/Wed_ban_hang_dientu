package org.soft.elec.service.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.models.PasswordResetOtp;
import org.soft.elec.entity.models.User;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.PasswordResetOtpRepository;
import org.soft.elec.repository.UserRepository;
import org.soft.elec.service.EmailService;
import org.soft.elec.service.PasswordResetService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetOtpRepository otpRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final TemplateEngine templateEngine;

    private static final int OTP_EXPIRY_MINUTES = 5;

    @Override
    public String initiatePasswordReset(String email) throws MessagingException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return "If an account with that email exists, an OTP has been sent to your email address.";
        }
        // Tạo OTP mới
        String otpCode = generateOTP();
        PasswordResetOtp otp = new PasswordResetOtp();
        otp.setOtp(otpCode);
        otp.setEmail(email);
        otp.setExpiryDate(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
        otp.setCreatedDate(LocalDateTime.now());
        otpRepository.save(otp);

        User user = optionalUser.get();
        String fullName = ((user.getFirstName() != null ? user.getFirstName() : "") + " " +
                (user.getLastName() != null ? user.getLastName() : "")).trim();

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
                otpRepository.findByEmailAndOtp(email, otpCode)
                        .orElseThrow(() -> new AppEx(ErrorCode.INVALID_OTP));
        if (passwordResetOtp.getExpiryDate().isBefore(LocalDateTime.now())) {
            otpRepository.delete(passwordResetOtp);
            throw new AppEx(ErrorCode.OTP_EXPIRED);
        }
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppEx(ErrorCode.USER_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        otpRepository.delete(passwordResetOtp);
    }

    private String generateOTP() {
        int randomPIN = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(randomPIN);
    }
}
