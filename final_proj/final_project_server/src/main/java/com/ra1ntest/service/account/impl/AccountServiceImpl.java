package com.ra1ntest.service.account.impl;

import com.ra1ntest.exception.EntityNotFoundException;
import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.persistance.entity.user.User;
import com.ra1ntest.repository.user.CustomerRepository;
import com.ra1ntest.repository.user.UserRepository;
import com.ra1ntest.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository<User> userRepository;
    private final CustomerRepository customerRepository;
    @Override
    public User getUserInfo(String login) {
        return userRepository
                .findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User not founded"));
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


}
