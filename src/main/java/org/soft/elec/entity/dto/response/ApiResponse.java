package org.soft.elec.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
  private int code;
  private String message;
  private T result;

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder().code(200).message("Success").result(data).build();
  }

  public static <T> ApiResponse<T> of(int code, String message, T data) {
    return ApiResponse.<T>builder().code(code).message(message).result(data).build();
  }

  public static <T> ApiResponse<T> error(int code, String message) {
    return ApiResponse.<T>builder().code(code).message(message).result(null).build();
  }
}
