package com.ra1ntest.facade.order.impl;

import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.api.dto.response.order.OrderDto;
import com.ra1ntest.api.dto.response.product.ProductPdpDto;
import com.ra1ntest.facade.cart.CartFacade;
import com.ra1ntest.facade.order.OrderFacade;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.cart.CartEntry;
import com.ra1ntest.persistance.entity.order.Order;
import com.ra1ntest.service.cart.CartService;
import com.ra1ntest.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public OrderDto findOrder() {
        List<Cart> cart = cartService.findCart();
        Cart cart1 = cart.get(0);
        Order order = orderService.findOrderByCartId(cart1.getId());
        List<CartItemsDto> cartEntries = cartService
                .getEntriesByCart(cart1)
                .stream()
                .map(CartItemsDto::new).toList();
        return new OrderDto(order, cartEntries);
    }

}
