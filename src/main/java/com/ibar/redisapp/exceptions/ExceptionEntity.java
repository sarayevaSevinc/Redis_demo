package com.ibar.redisapp.exceptions;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionEntity {
    private int code;
    private String description;

}
