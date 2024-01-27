package com.ra1ntest.service.order.impl;

import com.ra1ntest.api.dto.request.panel.ChangeOrderDto;
import com.ra1ntest.exception.EntityNotFoundException;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.order.Order;
import com.ra1ntest.persistance.type.OrderStatusType;
import com.ra1ntest.repository.order.OrderRepository;
import com.ra1ntest.service.order.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        LocalDateTime currentDateTime = LocalDateTime.now();
        order.setUpdatedAt(String.valueOf(currentDateTime));
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order findOrderByCartId(Long cartId) {
        return orderRepository.findByCartId(cartId);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order != null) {
            order.setOrderStatus(OrderStatusType.CANCELED.getOrderStatus());
            LocalDateTime currentDateTime = LocalDateTime.now();
            order.setUpdatedAt(String.valueOf(currentDateTime));
            orderRepository.save(order);
        } else {
            throw new EntityNotFoundException("Order not found by order id");
        }
    }

    @Override
    public void changeOrderStatus(ChangeOrderDto changeOrderDto) {
        Order order = orderRepository.findOrderById(changeOrderDto.getId());
        if (order != null) {
            order.setOrderStatus(changeOrderDto.getStatus());
            LocalDateTime currentDateTime = LocalDateTime.now();
            order.setUpdatedAt(String.valueOf(currentDateTime));
            orderRepository.save(order);
        } else {
            throw new EntityNotFoundException("Order not founded");
        }
    }


    @Override
    public List<Order> findOrdersByCustomerId(Long id) {
        List<Order> orders = orderRepository.findAllByCustomerId(id);
        return orders;
    }

}
