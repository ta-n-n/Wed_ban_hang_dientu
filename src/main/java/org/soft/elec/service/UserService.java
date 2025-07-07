package org.soft.elec.service;

import java.util.List;
import org.soft.elec.entity.dto.request.UserRequest;
import org.soft.elec.entity.dto.request.search.UserSearchRequest;
import org.soft.elec.entity.dto.response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {
  UserResponse createUser(UserRequest request);

  UserResponse updateUser(Integer id, UserRequest request);

  void deleteUser(Integer id);

  UserResponse getUserById(Integer id);

  List<UserResponse> getAllUsers();

  Page<UserResponse> searchUsers(UserSearchRequest request);
}
