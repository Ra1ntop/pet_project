package com.ra1ntest.facade.cart;

import com.ra1ntest.api.dto.response.cart.CartDto;
import com.ra1ntest.api.dto.response.cart.CartItemsDto;

import java.util.List;

public interface CartFacade {

    void updateQuantityInCart(Long productVariantId, int quantity);
    void addProductToCart(Long productVariantId, int quantity);

    void deleteProductFromCart(Long productVariantId);

    CartDto getActive();

    List<CartItemsDto> findItemsByCart();
}
