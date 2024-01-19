package com.ra1ntest.api.controller.auth;

import com.ra1ntest.api.dto.request.auth.RegisterDto;
import com.ra1ntest.api.dto.response.auth.AuthDto;
import com.ra1ntest.config.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthDto> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(authenticationService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody RegisterDto registerDto) {
        System.out.println(registerDto.getEmail() + registerDto.getPassword());
        return ResponseEntity.ok(authenticationService.login(registerDto));
    }
}
