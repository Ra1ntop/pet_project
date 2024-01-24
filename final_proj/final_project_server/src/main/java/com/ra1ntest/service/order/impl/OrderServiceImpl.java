package com.ra1ntest.service.order.impl;

import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.cart.CartEntry;
import com.ra1ntest.persistance.entity.order.Order;
import com.ra1ntest.repository.order.OrderRepository;
import com.ra1ntest.service.order.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.setCart(cart);
        order.setPrice(BigDecimal.valueOf(cart.getTotalPrice()));
        order.setCustomer(cart.getCustomer());
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order findOrderByCartId(Long cartId) {
        return orderRepository.findByCartId(cartId);
    }

    @Override
    public List<Order> findOrdersByCart(Cart cart) {
        return null;
    }

    @Override
    public List<Order> findOrdersByCustomerId(Long id) {
        List<Order> orders = orderRepository.findAllByCustomerId(id);
        return orders;
    }

    @Override
    public void updateOrder() {

    }
}
