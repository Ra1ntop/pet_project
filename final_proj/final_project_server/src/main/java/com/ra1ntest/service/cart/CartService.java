package com.ra1ntest.service.cart;

import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.cart.CartEntry;

import java.util.List;

public interface CartService {
    void addProductToCart(Long productVariantId, int quantity);

    void deleteProductFromCart(Long productVariantId);

    Cart getActive();

    List<CartEntry> getEntriesByCart(Cart cart);
}
