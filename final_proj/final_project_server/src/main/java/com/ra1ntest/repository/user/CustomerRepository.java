package com.ra1ntest.repository.user;

import com.ra1ntest.persistance.entity.user.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {

    Customer findCustomerByLoginAndEnabledTrue(String login);

    Boolean existsCustomerByLoginAndEnabledTrue(String login);

    Boolean existsCustomerByLoginAndEnabledTrueAndAccountNonLockedTrue(String login);

}
