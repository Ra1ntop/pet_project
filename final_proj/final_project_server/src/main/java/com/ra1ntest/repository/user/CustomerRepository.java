package com.ra1ntest.repository.user;

import com.ra1ntest.persistance.entity.user.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {
}
