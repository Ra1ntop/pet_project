package com.ra1ntest.api.dto.request.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterDto {
    private String email;
    private String firstName;
    private String lastName;
    private String age;
    private String password;
    private String country;

}
