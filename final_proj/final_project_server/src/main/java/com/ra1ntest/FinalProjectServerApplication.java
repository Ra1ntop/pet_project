package com.ra1ntest;

import com.ra1ntest.persistance.entity.user.Admin;
import com.ra1ntest.repository.user.AdminRepository;
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

    @Autowired
    private AdminRepository adminRepository;
    public static void main(String[] args) {
        SpringApplication.run(FinalProjectServerApplication.class, args);
    }


    //    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        Admin admin = new Admin();
        admin.setLogin("admin@rains.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setFirstName("Test admin First Name");
        admin.setLastName("Test admin Last Name");
        adminRepository.save(admin);
    }

}
