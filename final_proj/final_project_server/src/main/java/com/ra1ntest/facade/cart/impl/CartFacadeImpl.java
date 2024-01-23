package com.ra1ntest.facade.cart.impl;

import com.ra1ntest.api.dto.response.cart.CartDto;
import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.facade.cart.CartFacade;
import com.ra1ntest.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;

    @Override
    public void updateQuantityInCart(Long productVariantId, int quantity) {
        Integer productQuantity = cartService.findProductQuantity(productVariantId);
        if (productQuantity != null) {
            if (quantity < 0) {
                if (cartService.findProductQuantity(productVariantId) == 1) {
                    cartService.deleteProductFromCart(productVariantId);
                }
                if (productQuantity > 1) {
                    cartService.addProductToCart(productVariantId, quantity);
                }
            } else {
                addProductToCart(productVariantId, quantity);
            }

        }

    }

    @Override
    public void addProductToCart(Long productVariantId, int quantity) {
        cartService.addProductToCart(productVariantId, quantity);
    }

    @Override
    public void deleteProductFromCart(Long productVariantId) {
        if (StringUtils.isNumeric(productVariantId.toString())) {
            cartService.deleteProductFromCart(productVariantId);
        }
    }

    @Override
    public CartDto getActive() {
        return null;
    }

    @Override
    public List<CartItemsDto> findItemsByCart() {

        return cartService.getEntriesByCart(cartService.getCart()).stream()
                .map(product -> new CartItemsDto(product)).toList();
    }
}
