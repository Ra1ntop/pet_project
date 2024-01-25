package com.ra1ntest.service.account;

import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.persistance.entity.user.User;

import java.util.List;

public interface AccountService {
    User getUserInfo(String login);

    List<Customer> getCustomers();
}
