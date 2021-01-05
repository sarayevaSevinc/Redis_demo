package com.ibar.redisapp.controllers.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDTO {
    String name;
    String surname;
    int age;
    String password;
}
