package com.ra1ntest.api.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    private String email;
    private String password;
}
