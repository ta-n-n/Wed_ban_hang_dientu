package org.soft.elec.exception;

import lombok.Getter;
import org.soft.elec.entity.dto.response.ApiResponse;
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
  INVALID_ROLE(HttpStatus.BAD_REQUEST, "Invalid role."),

  // 404 Not Found
  BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "Brand not found."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found."),
  FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "File not found."),
  ENTITYFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "Entity file not found."),
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not found."),
  CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Category not found."),
  OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Option not found."),
  OPTION_VALUE_NOT_FOUND(HttpStatus.NOT_FOUND, "Option value not found."),
  ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "Order not found."),
  ORDER_PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Order product not found."),
  ORDER_PRODUCT_VARIATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Order product variation not found."),
  ORDER_PRODUCT_VARIATION_VALUE_NOT_FOUND(
      HttpStatus.NOT_FOUND, "Order product variation value not found."),
  PRODUCT_VARIANT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product variant not found."),
  VARIATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Variation not found."),
  VARIATION_VALUE_NOT_FOUND(HttpStatus.NOT_FOUND, "Variation value not found."),

  // 409 Conflict
  BRAND_ALREADY_EXISTS(HttpStatus.CONFLICT, "Brand already exists."),
  USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "User already exists."),
  FILE_ALREADY_EXISTS(HttpStatus.CONFLICT, "File already exists."),
  ENTITYFILE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Entity already exists."),
  PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Product already exists."),
  CATEGORY_ALREADY_EXISTS(HttpStatus.CONFLICT, "Category already exists."),
  OPTION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Option already exists."),
  OPTION_VALUE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Option value already exists."),
  ORDER_ALREADY_EXISTS(HttpStatus.CONFLICT, "Order already exists."),
  ORDER_PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Order product already exists."),
  ORDER_PRODUCT_VARIATION_ALREADY_EXISTS(
      HttpStatus.CONFLICT, "Order product variation already exists."),
  ORDER_PRODUCT_VARIATION_VALUE_ALREADY_EXISTS(
      HttpStatus.CONFLICT, "Order product variation value already exists."),
  PRODUCT_VARIANT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Product variant already exists."),
  VARIATION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Variation already exists."),
  VARIATION_VALUE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Variation value already exists."),
  CANNOT_DELETE_ADMIN(HttpStatus.CONFLICT, "Cannot delete admin"),
  USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "UserName already exists."),
  EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "Email already exists."),
  CANNOT_DELETE_CURRENT_USER(HttpStatus.CONFLICT, "Cannot delete your own account."),

  // 401 Unauthorized
  INVALID_LOGIN(HttpStatus.UNAUTHORIZED, "Incorrect email or password."),
  UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "User is not authenticated."),

  // 403 Forbidden
  NOT_ACTIVE(HttpStatus.FORBIDDEN, "This account is not active."),

  // 500 Internal Server Error
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
  TOKEN_GENERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while generating JWT token.");

  private final HttpStatus status;
  private final String message;

  ErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public int getCode() {
    return this.status.value();
  }

  public <T> ApiResponse<T> toResponse() {
    return ApiResponse.error(this.getCode(), this.message);
  }
}
