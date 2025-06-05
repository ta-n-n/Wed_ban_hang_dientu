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




    //404
    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "Brand not found."),
    BRAND_ALREADY_EXISTS(HttpStatus.CONFLICT, "Brand already exists."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "User already exists."),

    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "File not found."),
    FILE_ALREADY_EXISTS(HttpStatus.CONFLICT, "File already exists."),

    ENTITYFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "Entity file not found."),
    ENTITYFILE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Entity already exists."),

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not found."),
    PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Product already exists."),

    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Category not found."),
    CATEGORY_ALREADY_EXISTS(HttpStatus.CONFLICT, "Category already exists."),

    OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Option not found."),
    OPTION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Option already exists."),

    OPTION_VALUE_NOT_FOUND(HttpStatus.NOT_FOUND, "Option value not found."),
    OPTION_VALUE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Option already exists."),

    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "Order not found."),
    ORDER_ALREADY_EXISTS(HttpStatus.CONFLICT, "Order already exists."),

    ORDER_PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Order product not found."),
    ORDER_PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Order product already exists."),

    ORDER_PRODUCT_VARIATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Order product variation not found."),
    ORDER_PRODUCT_VARIATION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Order product variation already exists."),

    ORDER_PRODUCT_VARIATION_VALUE_NOT_FOUND(HttpStatus.NOT_FOUND, "Order product variation value not found."),
    ORDER_PRODUCT_VARIATION_VALUE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Order product variation value already exists."),

    PRODUCT_VARIANT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product variant not found."),
    PRODUCT_VARIANT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Product variant already exists."),

    VARIATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Variation not found."),
    VARIATION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Variation not found."),

    VARIATION_VALUE_NOT_FOUND(HttpStatus.NOT_FOUND, "Variation value not found."),
    VARIATION_VALUE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Variation value already exists."),

    CANNOT_DELETE_ADMIN(HttpStatus.CONFLICT, "Cannot delete admin"),






    USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "UserName already exists."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "Email already exists."),
    CANNOT_DELETE_CURRENT_USER(HttpStatus.CONFLICT, "Cannot delete your own account."),

    // Các lỗi 401 Unauthorized
    INVALID_LOGIN(HttpStatus.UNAUTHORIZED, "Incorrect email or password."),
    NOT_ACTIVE(HttpStatus.FORBIDDEN, "This account is not active."),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "User is not authenticated."),

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
