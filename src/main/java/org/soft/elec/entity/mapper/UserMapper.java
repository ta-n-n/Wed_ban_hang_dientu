package org.soft.elec.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.soft.elec.entity.dto.request.UserRequest;
import org.soft.elec.entity.dto.response.UserResponse;
import org.soft.elec.entity.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toEntity(UserRequest userRequest);

  UserResponse toResponse(User user);

  void updateEntity(UserRequest userRequest, @MappingTarget User user);
}
