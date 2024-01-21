package com.ra1ntest.facade.cart.impl;

import com.ra1ntest.api.dto.response.cart.CartDto;
import com.ra1ntest.facade.cart.CartFacade;
import com.ra1ntest.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;

    @Override
    public void addProductToCart(Long productVariantId, int quantity) {
        cartService.addProductToCart(productVariantId, quantity);
    }

    @Override
    public void deleteProductFromCart(Long productVariantId) {

    }

    @Override
    public CartDto getActive() {
        return null;
    }
}
