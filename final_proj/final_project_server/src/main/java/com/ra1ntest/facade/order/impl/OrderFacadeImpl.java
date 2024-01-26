package com.ra1ntest.facade.order.impl;

import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.api.dto.response.order.OrderDto;
import com.ra1ntest.facade.order.OrderFacade;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.order.Order;
import com.ra1ntest.service.cart.CartService;
import com.ra1ntest.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final CartService cartService;

    @Override
    public void createOrder() {
        Cart cart = cartService.getCart();
        if (cart.getTotalPrice() > 0) {
            Order order = orderService.createOrder(cartService.getCart());
            cartService.setOrdered(cart, order);
        }
    }

    @Override
    public OrderDto findOrder(Cart cart) {
        Order order = orderService.findOrderByCartId(cart.getId());
        List<CartItemsDto> cartEntries = cartService
                .getEntriesByCart(cart)
                .stream()
                .map(CartItemsDto::new).toList();
        return new OrderDto(order, cartEntries);
    }

    @Override
    public List<OrderDto> findOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Cart> carts = cartService.findCart();
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            orderDtos.add(findOrder(cart));
        }

        System.out.println(orderDtos);
        Collections.reverse(orderDtos);
        return orderDtos;
    }

    @Override
    public List<OrderDto> findOrdersByCustomerId(Long customerId) {
        orderService.findOrdersByCustomerId(customerId);
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Cart> carts = cartService.findCartsByCustomerId(customerId);
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            orderDtos.add(findOrder(cart));
        }

        System.out.println(orderDtos);
        Collections.reverse(orderDtos);
        return orderDtos;
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderService.cancelOrder(orderId);
    }

}
