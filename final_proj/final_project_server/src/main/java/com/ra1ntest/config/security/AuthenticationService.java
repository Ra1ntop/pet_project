package com.ra1ntest.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ra1ntest.api.dto.request.auth.RegisterDto;
import com.ra1ntest.api.dto.response.auth.AuthDto;
import com.ra1ntest.persistance.entity.token.Token;
import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.persistance.entity.user.User;
import com.ra1ntest.repository.token.TokenRepository;
import com.ra1ntest.repository.user.CustomerRepository;
import com.ra1ntest.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final UserRepository<User> userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;


    public AuthDto register(RegisterDto registerDto) {
        if (userRepository.existsByLogin(registerDto.getEmail())) {
            throw new RuntimeException("this email is already exists");
        }
        Customer customer = new Customer();
        customer.setLogin(registerDto.getEmail());
        customer.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        customer = customerRepository.save(customer);
        String jwttoken = jwtService.generateToken(customer);
        Token token = new Token();
        token.setToken(jwttoken);
        token.setUser(customer);
        tokenRepository.save(token);
        return new AuthDto(jwttoken);
    }

    public AuthDto login(RegisterDto registerDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerDto.getEmail(), registerDto.getPassword())
        );
        var user = userRepository.findByLogin(registerDto.getEmail()).orElseThrow();
        String jwttoken = jwtService.generateToken(user);
        Token token = new Token();
        token.setToken(jwttoken);
        token.setUser(user);
        tokenRepository.save(token);
        return new AuthDto(jwttoken);

    }

}
