package org.soft.elec.entity.enums;

import lombok.Getter;
import org.soft.elec.exception.ApiError;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Các lỗi 400 Bad Request
    INVALID_FILE_TYPE(
            HttpStatus.BAD_REQUEST, "Invalid file type. Only PNG, JPG, and JPEG are allowed."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation error occurred."),
    PASSWORD_NULL(HttpStatus.BAD_REQUEST, "Password must be provided."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "Invalid input."),
    INVALID_OTP(HttpStatus.BAD_REQUEST, "Invalid OTP."),
    OTP_EXPIRED(HttpStatus.BAD_REQUEST, "OTP has expired."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Old password is incorrect."),
//    NEW_PASSWORD_MUST_BE_DIFFERENT(
//            HttpStatus.BAD_REQUEST, "New password must be different from current password."),
//    INVALID_TIME_RANGE(HttpStatus.BAD_REQUEST, "Start time must be before end time."),

    USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "UserName already exists."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "Email already exists."),
    CANNOT_DELETE_CURRENT_USER(HttpStatus.CONFLICT, "Cannot delete your own account."),

    // Các lỗi 401 Unauthorized
    INVALID_LOGIN(HttpStatus.UNAUTHORIZED, "Incorrect email or password."),
    NOT_ACTIVE(HttpStatus.FORBIDDEN, "This account is not active."),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "User is not authenticated."),

    // Các lỗi 404 Not Found
    CANNOT_DELETE_ADMIN(HttpStatus.CONFLICT, "Cannot delete admin"),

    // Lỗi 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiError toApiError(String customMessage) {
        return new ApiError(customMessage);
    }
}
