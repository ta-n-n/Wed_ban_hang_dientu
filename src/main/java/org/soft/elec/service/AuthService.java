package org.soft.elec.service;

import com.nimbusds.jose.JOSEException;
import org.soft.elec.entity.dto.request.AuthRequest;
import org.soft.elec.entity.dto.request.IntrospectRequest;
import org.soft.elec.entity.dto.request.LogoutRequest;
import org.soft.elec.entity.dto.request.RefreshRequest;
import org.soft.elec.entity.dto.response.AuthResponse;
import org.soft.elec.entity.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthService {
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException;
    AuthResponse authenticate(AuthRequest request);
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
