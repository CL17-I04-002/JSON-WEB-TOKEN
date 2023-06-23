package com.proyecto.jwt.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
@RequiredArgsConstructor
public class AuthenticationRequest {
    private final String username;
    private final String password;
}
