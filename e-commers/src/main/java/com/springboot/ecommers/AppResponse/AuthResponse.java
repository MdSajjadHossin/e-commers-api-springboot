package com.springboot.ecommers.AppResponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {

    private String jwt;
    private String message;
}
