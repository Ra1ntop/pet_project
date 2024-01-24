package com.ra1ntest.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ra1ntest.persistance.type.PermissionType.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class AuthorizationSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/open/**", "/api/auth/**", "/swagger-ui/**", "v3/api-docs/**").permitAll()
                        .requestMatchers(GET, "/api/customer/", "/api/customer/cart/", "/api/customer/order/").hasAuthority(CUSTOMER_READ.name())
                        .requestMatchers(POST, "/api/customer/").hasAuthority(CUSTOMER_CREATE.name())
                        .requestMatchers(PUT, "/api/customer/", "/api/customer/order/cancel-order/", "/api/customer/order/").hasAuthority(CUSTOMER_UPDATE.name())
                        .requestMatchers(DELETE, "/api/customer/").hasAuthority(CUSTOMER_DELETE.name())

                        .requestMatchers(GET, "/api/admin/").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(POST, "/api/admin/").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/api/admin/").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/admin/").hasAuthority(ADMIN_DELETE.name())
                        .anyRequest().authenticated()

                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
