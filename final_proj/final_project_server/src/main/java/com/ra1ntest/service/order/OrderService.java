package com.ra1ntest.service.order;

import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.order.Order;
import com.ra1ntest.service.CrudService;

import java.util.List;

public interface OrderService {
    void createOrder(Cart cart);

    Order findOrderByCartId(Long cartid);

    List<Order> findOrdersByCart(Cart cart);

    List<Order> findOrdersByCustomerId(Long id);

    void updateOrder();
}
