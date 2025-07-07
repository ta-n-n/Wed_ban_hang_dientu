package org.soft.elec.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.soft.elec.entity.dto.request.*;
import org.soft.elec.entity.dto.response.*;
import org.soft.elec.entity.models.InvalidatedToken;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.InvalidatedTokenRepository;
import org.soft.elec.repository.UserRepository;
import org.soft.elec.service.AuthService;
import org.soft.elec.utils.JwtTokenUtil;
import org.soft.elec.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final InvalidatedTokenRepository invalidatedTokenRepository;
  private final PasswordUtil passwordUtil;
  private final JwtTokenUtil jwtTokenUtil;

  @Value("${security.jwt.access-secret}")
  private String accessSecret;

  @Value("${security.jwt.refresh-secret}")
  private String refreshSecret;

  @Override
  public AuthResponse authenticate(AuthRequest request) {
    var user =
        userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new AppException(ErrorCode.INVALID_LOGIN));
    if (!passwordUtil.matches(request.getPassword(), user.getPassword())) {
      throw new AppException(ErrorCode.INVALID_LOGIN);
    }
    if (!user.isEnabled()) {
      throw new AppException(ErrorCode.NOT_ACTIVE);
    }
    var accessToken = jwtTokenUtil.generateToken(user, 360, accessSecret, "access");
    var refreshToken = jwtTokenUtil.generateToken(user, 7 * 24 * 60, refreshSecret, "refresh");

    return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
  }

  @Override
  public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException {
    jwtTokenUtil.verifyToken(request.getAccessToken(), accessSecret);
    return IntrospectResponse.builder().valid(true).build();
  }

  @Override
  public void logout(LogoutRequest request) throws ParseException, JOSEException {
    try {
      if (request.getAccessToken() != null) {
        invalidateToken(request.getAccessToken(), accessSecret);
      }
      if (request.getRefreshToken() != null) {
        invalidateToken(request.getRefreshToken(), refreshSecret);
      }
    } catch (AppException e) {
      log.info("Token already expired or invalid: {}", e.getMessage());
    }
  }

  private void invalidateToken(String token, String secret) throws ParseException, JOSEException {
    SignedJWT jwt = jwtTokenUtil.verifyToken(token, secret);
    String jwtId = jwt.getJWTClaimsSet().getJWTID();
    Date exp = jwt.getJWTClaimsSet().getExpirationTime();

    invalidatedTokenRepository.save(
        InvalidatedToken.builder()
            .token(jwtId)
            .expiresAt(exp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
            .build());
  }

  @Override
  public AuthResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
    SignedJWT jwt = jwtTokenUtil.verifyToken(request.getRefreshToken(), refreshSecret);

    String jwtId = jwt.getJWTClaimsSet().getJWTID();
    if (invalidatedTokenRepository.existsByToken(jwtId)) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }
    String email = jwt.getJWTClaimsSet().getSubject();
    var user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
    String newAccessToken = jwtTokenUtil.generateToken(user, 360, accessSecret, "access");
    return AuthResponse.builder()
        .accessToken(newAccessToken)
        .refreshToken(request.getRefreshToken())
        .build();
  }
}
