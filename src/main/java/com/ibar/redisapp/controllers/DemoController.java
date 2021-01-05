package com.ibar.redisapp.controllers;

import com.ibar.redisapp.controllers.dto.UserRequestDTO;
import com.ibar.redisapp.controllers.dto.UserResponseDTO;
import com.ibar.redisapp.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping("/users")
public class DemoController {
    private final UserService userService;


    public DemoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam long id) {
        return new ResponseEntity<>(userService.getUserService(id), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO user) {
        log.info("adding user ......");

        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }
}
