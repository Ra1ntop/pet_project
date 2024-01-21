package com.ra1ntest.facade.cart;

import com.ra1ntest.api.dto.response.cart.CartDto;

public interface CartFacade {

    void addProductToCart(Long productVariantId, int quantity);

    void deleteProductFromCart(Long productVariantId);

    CartDto getActive();
}
