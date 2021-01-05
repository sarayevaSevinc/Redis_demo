package com.ibar.redisapp.utilities;

import com.ibar.redisapp.controllers.dto.UserRequestDTO;
import com.ibar.redisapp.controllers.dto.UserResponseDTO;
import com.ibar.redisapp.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestDtoToUser(UserRequestDTO userRequestDTO);
    UserResponseDTO userToUserResponseDto(User user);
}
