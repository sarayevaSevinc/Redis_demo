package com.ibar.redisapp.services.impl;

import com.ibar.redisapp.controllers.dto.UserRequestDTO;
import com.ibar.redisapp.controllers.dto.UserResponseDTO;
import com.ibar.redisapp.entities.User;
import com.ibar.redisapp.exceptions.AccountNotFoundException;
import com.ibar.redisapp.repositories.UserDbRepository;
import com.ibar.redisapp.repositories.UserRedisRepository;
import com.ibar.redisapp.services.UserService;
import com.ibar.redisapp.utilities.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {
    private final UserDbRepository userDbRepository;
    UserRedisRepository userRedisRepository;

    public UserServiceImpl(UserDbRepository userDbRepository, UserRedisRepository userRedisRepository) {
        this.userDbRepository = userDbRepository;
        this.userRedisRepository = userRedisRepository;
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        User user = this.userDbRepository.save(UserMapper.INSTANCE.requestDtoToUser(userRequestDTO));
        this.userRedisRepository.addUser(user);
        log.info("user has been added.. id = " + user.getId());
        return UserMapper.INSTANCE.userToUserResponseDto(userRedisRepository.getUser(user.getId()));
    }

    @Override
    public UserResponseDTO getUserFromDB(long id) {
        log.info("getting user from db... ");
        Optional<User> byId = this.userDbRepository.findById(id);
        if (byId.isPresent()) {
            log.info("adding user to redis ...");
            this.userRedisRepository.addUser(byId.get());
            return UserMapper.INSTANCE.userToUserResponseDto(byId.get());
        }
        throw new AccountNotFoundException("there is no user with this id in database..");

    }

    public UserResponseDTO getUserService(long id) {
        log.info("getting user service has started...");
        User user = this.userRedisRepository.getUser(id);
        if (user == null) {
            log.info("user has not found from redis..");
            log.info("searching user from db");
            return getUserFromDB(id);
        }
        return UserMapper.INSTANCE.userToUserResponseDto(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        this.userDbRepository.findAll().forEach(x -> users.add(x));
        return users;
    }


}
