//package com.ra1ntest.config.security;
//
//import com.ra1ntest.persistance.entity.user.User;
//import com.ra1ntest.repository.user.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class OurUserDetailsService implements UserDetailsService {
//
//    private final UserRepository<User> userUserRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userUserRepository
//                .findByLogin(username)
//                .orElseThrow(() -> new RuntimeException("User not founded"));
//    }
//}
