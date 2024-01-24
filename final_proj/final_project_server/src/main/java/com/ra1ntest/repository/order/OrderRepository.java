package com.ra1ntest.repository.order;

import com.ra1ntest.persistance.entity.order.Order;
import com.ra1ntest.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order> {
    Order findByCartId(Long cartId);

    Order findOrderById(Long Id);
    List<Order> findAllByCustomerId(Long customerId);
}
