package org.soft.elec.controller;

import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.soft.elec.entity.dto.request.AuthRequest;
import org.soft.elec.entity.dto.request.IntrospectRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.AuthResponse;
import org.soft.elec.entity.dto.response.IntrospectResponse;
import org.soft.elec.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

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
//
//    @PostMapping("/logout")
//
//    @PostMapping("/refresh")
//
//    @PostMapping("/forgot-password")
//
//    @PostMapping("/reset-password")
}
