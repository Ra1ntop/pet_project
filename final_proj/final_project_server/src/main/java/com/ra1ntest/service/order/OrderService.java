package com.ra1ntest.service.order;

import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.order.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Cart cart);

    Order findOrderByCartId(Long cartid);

    void cancelOrder(Long orderId);

    List<Order> findOrdersByCart(Cart cart);

    List<Order> findOrdersByCustomerId(Long id);

    void updateOrder();
}
