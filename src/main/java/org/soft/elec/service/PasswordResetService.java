package org.soft.elec.service;

import jakarta.mail.MessagingException;

public interface PasswordResetService {
    String initiatePasswordReset(String email) throws MessagingException;
    void resetPassword(String email, String otpCode, String newPassword);
}
