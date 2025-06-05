package org.soft.elec.controller;

import com.nimbusds.jose.JOSEException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.AuthRequest;
import org.soft.elec.entity.dto.request.IntrospectRequest;
import org.soft.elec.entity.dto.request.LogoutRequest;
import org.soft.elec.entity.dto.request.RefreshRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.AuthResponse;
import org.soft.elec.entity.dto.response.IntrospectResponse;
import org.soft.elec.service.AuthService;
import org.soft.elec.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> auth(@Valid @RequestBody AuthRequest request) {
        var result = authService.authenticate(request);
        return ApiResponse.<AuthResponse>builder()
                .success(true)
                .data(
                        AuthResponse.builder()
                                .accessToken(result.getAccessToken())
                                .refreshToken(result.getRefreshToken())
                                .build())
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> auth(@RequestBody IntrospectRequest request)
            throws JOSEException {
        var result = authService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().success(true).data(result).build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authService.logout(request);
        return ApiResponse.<Void>builder().success(true).build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refreshToken(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authService.refreshToken(request);
        return ApiResponse.<AuthResponse>builder().success(true).data(result).build();
    }

    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@RequestParam("email") @NotBlank String email) {
        try {
            String message = passwordResetService.initiatePasswordReset(email);
            return ApiResponse.<String>builder().success(true).data(message).build();
        } catch (MessagingException e) {
            return ApiResponse.<String>builder()
                    .success(false)
                    .data("OTP sending failed: " + e.getMessage())
                    .build();
        }
    }

    @PostMapping("/reset-password")
    public ApiResponse<String> resetPassword(
            @RequestParam("email") @NotBlank String email,
            @RequestParam("otp") @NotBlank String otp,
            @RequestParam("newPassword") @NotBlank String newPassword) {
        passwordResetService.resetPassword(email, otp, newPassword);
        return ApiResponse.<String>builder()
                .success(true)
                .data("Password updated successfully.")
                .build();
    }
}
