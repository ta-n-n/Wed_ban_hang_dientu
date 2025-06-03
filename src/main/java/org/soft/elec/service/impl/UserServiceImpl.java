package org.soft.elec.service.impl;

import lombok.RequiredArgsConstructor;
import org.soft.elec.entity.dto.request.UserRequest;
import org.soft.elec.entity.dto.response.UserResponse;
import org.soft.elec.entity.enums.ErrorCode;
import org.soft.elec.entity.mapper.UserMapper;
import org.soft.elec.entity.models.User;
import org.soft.elec.exception.AppEx;
import org.soft.elec.repository.UserRepository;
import org.soft.elec.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private void checkUserExist(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new AppEx(ErrorCode.USER_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Integer id, UserRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.USER_NOT_FOUND));
        userMapper.updateEntity(request, user);
        User updated = userRepository.save(user);
        return userMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id){
        checkUserExist(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppEx(ErrorCode.USER_NOT_FOUND));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
}
