package org.soft.elec.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.soft.elec.constant.enums.Role;
import org.soft.elec.entity.dto.request.UserRequest;
import org.soft.elec.entity.dto.request.search.UserSearchRequest;
import org.soft.elec.entity.dto.response.UserResponse;
import org.soft.elec.entity.mapper.UserMapper;
import org.soft.elec.entity.models.User;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.UserRepository;
import org.soft.elec.service.UserService;
import org.soft.elec.specification.UserSpecification;
import org.soft.elec.utils.PageUtil;
import org.soft.elec.utils.PasswordUtil;
import org.soft.elec.utils.SpecUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordUtil passwordUtil;

  @Override
  @Transactional
  public UserResponse createUser(UserRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
    validateRole(request.getRole());
    request.setPassword(passwordUtil.encode(request.getPassword()));
    User user = userMapper.toEntity(request);
    User saved = userRepository.save(user);
    return userMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public UserResponse updateUser(Integer id, UserRequest request) {
    User user = getUserOrThrow(id);

    if (!user.getEmail().equals(request.getEmail()) &&
            userRepository.existsByEmail(request.getEmail())) {
      throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
    validateRole(request.getRole());
    if (!passwordUtil.matches(request.getPassword(), user.getPassword())) {
      request.setPassword(passwordUtil.encode(request.getPassword()));
    }
    userMapper.updateEntity(request, user);
    User updated = userRepository.save(user);
    return userMapper.toResponse(updated);
  }

  @Override
  @Transactional
  public void deleteUser(Integer id) {
    User user = getUserOrThrow(id);
    userRepository.delete(user);
  }

  @Override
  public UserResponse getUserById(Integer id) {
    User user = getUserOrThrow(id);
    return userMapper.toResponse(user);
  }

  @Override
  public List<UserResponse> getAllUsers() {
    return userRepository.findAll().stream()
        .map(userMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Page<UserResponse> searchUsers(UserSearchRequest request) {
    Specification<User> spec = null;

    spec = SpecUtil.add(spec, UserSpecification.keywordContains(request.getKeyword()));
    spec = SpecUtil.add(spec, UserSpecification.hasRole(request.getRole()));
    spec = SpecUtil.add(spec, UserSpecification.isEnabled(request.getEnabled()));

    Pageable pageable = PageUtil.getPageable(request);

    Page<User> page = userRepository.findAll(spec, pageable);
    return page.map(userMapper::toResponse);
  }

  // === Helper ===
  private User getUserOrThrow(Integer id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
  }

  private void validateRole(String role) {
    try {
      Role.valueOf(role);
    } catch (IllegalArgumentException ex) {
      throw new AppException(ErrorCode.INVALID_ROLE);
    }
  }
}
