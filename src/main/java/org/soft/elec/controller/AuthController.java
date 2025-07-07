package org.soft.elec.controller;

import com.nimbusds.jose.JOSEException;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.text.ParseException;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.*;
import org.soft.elec.entity.dto.response.*;
import org.soft.elec.service.AuthService;
import org.soft.elec.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final PasswordResetService passwordResetService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<AuthResponse>> auth(@Valid @RequestBody AuthRequest request) {
    var result = authService.authenticate(request);
    var response =
        AuthResponse.builder()
            .accessToken(result.getAccessToken())
            .refreshToken(result.getRefreshToken())
            .build();
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @PostMapping("/introspect")
  public ResponseEntity<ApiResponse<IntrospectResponse>> introspect(
      @RequestBody IntrospectRequest request) throws JOSEException {
    var result = authService.introspect(request);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  @PostMapping("/logout")
  public ResponseEntity<ApiResponse<Void>> logout(@RequestBody LogoutRequest request)
      throws ParseException, JOSEException {
    authService.logout(request);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @PostMapping("/refresh")
  public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(@RequestBody RefreshRequest request)
      throws ParseException, JOSEException {
    var result = authService.refreshToken(request);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<ApiResponse<String>> forgotPassword(
      @RequestParam("email") @NotBlank String email) {
    try {
      String message = passwordResetService.initiatePasswordReset(email);
      return ResponseEntity.ok(ApiResponse.success(message));
    } catch (MessagingException e) {
      return ResponseEntity.ok(ApiResponse.error(500, "OTP sending failed: " + e.getMessage()));
    }
  }

  @PostMapping("/reset-password")
  public ResponseEntity<ApiResponse<String>> resetPassword(
      @RequestParam("email") @NotBlank String email,
      @RequestParam("otp") @NotBlank String otp,
      @RequestParam("newPassword") @NotBlank String newPassword) {
    passwordResetService.resetPassword(email, otp, newPassword);
    return ResponseEntity.ok(ApiResponse.success("Password updated successfully."));
  }
}
