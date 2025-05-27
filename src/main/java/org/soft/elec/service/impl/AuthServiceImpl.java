package org.soft.elec.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.soft.elec.entity.dto.request.AuthRequest;
import org.soft.elec.entity.dto.request.IntrospectRequest;
import org.soft.elec.entity.dto.request.LogoutRequest;
import org.soft.elec.entity.dto.request.RefreshRequest;
import org.soft.elec.entity.dto.response.AuthResponse;
import org.soft.elec.entity.dto.response.IntrospectResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.models.User;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.InvalidatedTokenRepository;
import org.soft.elec.repository.UserRepository;
import org.soft.elec.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.soft.elec.entity.models.InvalidatedToken;


import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final InvalidatedTokenRepository invalidatedTokenRepository;

    @Value("${security.jwt.access-secret}")
    protected String ACCESS_SECRET;

    @Value("${security.jwt.refresh-secret}")
    protected String REFRESH_SECRET;

    public AuthServiceImpl(
            UserRepository userRepository, InvalidatedTokenRepository invalidatedTokenRepository) {
        this.userRepository = userRepository;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException {
        var token = request.getAccessToken();
        verifyToken(token, ACCESS_SECRET);
        return IntrospectResponse.builder().valid(true).build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        var user =
                userRepository
                        .findByEmail(request.getEmail())
                        .orElseThrow(() -> new AppEx(ErrorCode.INVALID_LOGIN));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppEx(ErrorCode.INVALID_LOGIN);
        }
        if (!user.isEnabled()) {
            throw new AppEx(ErrorCode.NOT_ACTIVE);
        }
        var accessToken = generateToken(user, 360, ACCESS_SECRET, "access"); // token sống 6 tiếng
        var refreshToken =
                generateToken(user, 7 * 24 * 60, REFRESH_SECRET, "refresh"); // token sống 7 ngày
        return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            if (request.getRefreshToken() != null) {
                var refreshJWT = verifyToken(request.getRefreshToken(), REFRESH_SECRET);
                String refreshJit = refreshJWT.getJWTClaimsSet().getJWTID();
                Date refreshExp = refreshJWT.getJWTClaimsSet().getExpirationTime();
                invalidatedTokenRepository.save(
                        InvalidatedToken.builder().id(refreshJit).expiryTime(refreshExp).build());
            }
            if (request.getAccessToken() != null) {
                var accessJWT = verifyToken(request.getAccessToken(), ACCESS_SECRET);
                String accessJit = accessJWT.getJWTClaimsSet().getJWTID();
                Date accessExp = accessJWT.getJWTClaimsSet().getExpirationTime();
                invalidatedTokenRepository.save(
                        InvalidatedToken.builder().id(accessJit).expiryTime(accessExp).build());
            }
        } catch (AppEx e) {
            log.info("Token already expired or invalid");
        }
    }

    @Override
    public AuthResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getRefreshToken(), REFRESH_SECRET);

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        if (invalidatedTokenRepository.existsById(jit)) {
            throw new AppEx(ErrorCode.UNAUTHENTICATED);
        }
        var email = signedJWT.getJWTClaimsSet().getSubject();
        var user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new AppEx(ErrorCode.UNAUTHENTICATED));
        var newAccessToken = generateToken(user, 360, ACCESS_SECRET, "access");

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.getRefreshToken())
                .build();
    }

    private SignedJWT verifyToken(String token, String secret) throws JOSEException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            JWSVerifier verifier = new MACVerifier(secret.getBytes());
            boolean verified = signedJWT.verify(verifier);
            if (!(verified && expiryTime.after(new Date()))) {
                throw new AppEx(ErrorCode.UNAUTHENTICATED);
            }
            if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
                throw new AppEx(ErrorCode.UNAUTHENTICATED);
            }
            return signedJWT;
        } catch (ParseException e) {
            throw new AppEx(ErrorCode.UNAUTHENTICATED);
        }
    }

    private String generateToken(User user, int expirationMinutes, String secret, String tokenType) {
        try {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            String fullName = (user.getFirstName() != null ? user.getFirstName() : "") + " " +
                    (user.getLastName() != null ? user.getLastName() : "");
            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getEmail())
                    .issuer("meeting-room-booking")
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
                    .jwtID(UUID.randomUUID().toString())
                    .claim("scope", buildScope(user.getRole()))
                    .claim("name", fullName.trim())
                    .claim("token_type", tokenType)
                    .build();
            JWSObject jwsObject = new JWSObject(header, new Payload(jwtClaimsSet.toJSONObject()));
            jwsObject.sign(new MACSigner(secret.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Error generating token", e);
            throw new RuntimeException(e);
        }
    }


    private String buildScope(String role) {
        if (role == null || role.isEmpty()) {
            return "";
        }
        return "ROLE_" + role.toUpperCase();
    }

}
