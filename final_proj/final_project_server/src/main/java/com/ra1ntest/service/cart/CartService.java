package com.ra1ntest.service.cart;

import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.cart.CartEntry;
import com.ra1ntest.persistance.entity.order.Order;

import java.util.List;

public interface CartService {
    void addProductToCart(Long productVariantId, int quantity);

    Integer findProductQuantity(Long productVariantId);

    void deleteProductFromCart(Long productVariantId);

    Cart getActive();

    void setOrdered(Cart cart, Order order);


    Cart getCart();

    List<Cart> findCart();
    List<CartEntry> getEntriesByCart(Cart cart);

}
