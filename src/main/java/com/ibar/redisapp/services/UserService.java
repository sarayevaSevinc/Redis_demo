package com.ibar.redisapp.services;

import com.ibar.redisapp.controllers.dto.UserRequestDTO;
import com.ibar.redisapp.controllers.dto.UserResponseDTO;
import com.ibar.redisapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    UserResponseDTO addUser(UserRequestDTO user);
    UserResponseDTO getUserFromDB(long id);
    List<User> getAllUsers();
    UserResponseDTO getUserService(long id);
}
