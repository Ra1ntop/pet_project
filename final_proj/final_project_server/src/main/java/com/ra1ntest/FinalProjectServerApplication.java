package com.ra1ntest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FinalProjectServerApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(FinalProjectServerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
//        String password = "Test123!";
//        String hash = passwordEncoder.encode(password);
//        System.out.println("hash = " + hash);
    }

}
