package com.ra1ntest.repository.cart;

import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends BaseRepository<Cart> {

    Optional<Cart> findByCustomerAndActiveTrue(Customer customer);

    Optional<Cart> findAllByCustomer(Customer customer);

}
