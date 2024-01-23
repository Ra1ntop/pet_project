package com.ra1ntest.facade.order.impl;

import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.api.dto.response.order.OrderDto;
import com.ra1ntest.api.dto.response.product.ProductPdpDto;
import com.ra1ntest.facade.cart.CartFacade;
import com.ra1ntest.facade.order.OrderFacade;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.cart.CartEntry;
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
            orderService.createOrder(cartService.getCart());
            cartService.setDisable(cart);
        }
    }

    @Override
    public List<OrderDto> findOrders() {
        Long customerId = cartService.getCart().getCustomer().getId();
        List<Cart> carts = cartService.findCart();
        List<CartEntry> cartEntries;

        return orderService.findOrdersByCustomerId(customerId).stream().map(
                order -> {
                    Cart cart = order.getCart();
                    List<CartEntry> entries = cartService.getEntriesByCart(cart);
                    System.out.println("entries = " + entries);
                    return new OrderDto(order);
                }
        ).toList();
    }

}
