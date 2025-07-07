package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.UserRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.UserResponse;
import org.soft.elec.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserRequest request) {
    UserResponse created = userService.createUser(request);
    return ResponseEntity.ok(ApiResponse.success(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> update(
      @PathVariable Integer id, @Valid @RequestBody UserRequest request) {
    UserResponse updated = userService.updateUser(id, request);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Integer id) {
    UserResponse user = userService.getUserById(id);
    return ResponseEntity.ok(ApiResponse.success(user));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
    List<UserResponse> users = userService.getAllUsers();
    return ResponseEntity.ok(ApiResponse.success(users));
  }
}
