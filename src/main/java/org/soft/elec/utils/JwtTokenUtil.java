package org.soft.elec.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.soft.elec.constant.enums.Role;
import org.soft.elec.entity.models.User;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

  private final FullNameUtil fullNameUtil;

  public String generateToken(User user, int expirationMinutes, String secret, String tokenType) {
    try {
      JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
      String fullName = fullNameUtil.buildFullName(user);

      JWTClaimsSet claimsSet =
          new JWTClaimsSet.Builder()
              .subject(user.getEmail())
              .issuer("elec")
              .issueTime(new Date())
              .expirationTime(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
              .jwtID(UUID.randomUUID().toString())
              .claim("scope", buildScope(user.getRole()))
              .claim("name", fullName)
              .claim("token_type", tokenType)
              .build();

      SignedJWT signedJWT = new SignedJWT(header, claimsSet);
      signedJWT.sign(new MACSigner(secret.getBytes()));

      return signedJWT.serialize();

    } catch (JOSEException e) {
      log.error("Error generating token", e);
      throw new AppException(ErrorCode.TOKEN_GENERATION_FAILED);
    }
  }

  public SignedJWT verifyToken(String token, String secret) throws JOSEException {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);
      JWSVerifier verifier = new MACVerifier(secret.getBytes());
      boolean isValid = signedJWT.verify(verifier);
      Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();

      if (!(isValid && exp.after(new Date()))) {
        throw new AppException(ErrorCode.UNAUTHENTICATED);
      }

      return signedJWT;
    } catch (ParseException e) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }
  }

  private String buildScope(Role role) {
    return role == null ? "" : "ROLE_" + role.name().toUpperCase();
  }
}
