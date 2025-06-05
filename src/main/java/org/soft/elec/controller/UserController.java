package org.soft.elec.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.soft.elec.entity.dto.request.UserRequest;
import org.soft.elec.entity.dto.response.ApiResponse;
import org.soft.elec.entity.dto.response.UserResponse;
import org.soft.elec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping
  public ApiResponse<UserResponse> create(@RequestBody @Valid UserRequest request) {
    return ApiResponse.<UserResponse>builder()
        .success(true)
        .data(userService.createUser(request))
        .build();
  }

  @PutMapping("/{id}")
  public ApiResponse<UserResponse> update(
      @PathVariable Integer id, @RequestBody @Valid UserRequest request) {
    return ApiResponse.<UserResponse>builder()
        .success(true)
        .data(userService.updateUser(id, request))
        .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> delete(@PathVariable Integer id) {
    userService.deleteUser(id);
    return ApiResponse.<String>builder().success(true).data("user has been deleted").build();
  }

  @GetMapping("/{id}")
  public ApiResponse<UserResponse> getById(@PathVariable Integer id) {
    return ApiResponse.<UserResponse>builder()
        .success(true)
        .data(userService.getUserById(id))
        .build();
  }

  @GetMapping
  public ApiResponse<List<UserResponse>> getAll() {
    return ApiResponse.<List<UserResponse>>builder()
        .success(true)
        .data(userService.getAllUsers())
        .build();
  }
}
