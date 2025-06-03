package org.soft.elec.service;

import org.soft.elec.entity.dto.request.UserRequest;
import org.soft.elec.entity.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Integer id, UserRequest request);
    void deleteUser(Integer id);
    UserResponse getUserById(Integer id);
    List<UserResponse> getAllUsers();
}
