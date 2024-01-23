package com.ra1ntest.facade.order.impl;

import com.ra1ntest.facade.cart.CartFacade;
import com.ra1ntest.facade.order.OrderFacade;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.service.cart.CartService;
import com.ra1ntest.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
    private final OrderService orderService;
    private final CartService cartService;

    @Override
    public void createOrder() {
        Cart cart = cartService.getCart();
        if (cart.getTotalPrice() > 0) {
            orderService.createOrder(cartService.getCart());
            cartService.setDisable(cart);
        }
    }
}
