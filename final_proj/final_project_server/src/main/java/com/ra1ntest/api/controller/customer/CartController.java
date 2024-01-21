package com.ra1ntest.api.controller.customer;

import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.api.dto.response.product.ProductPlpDto;
import com.ra1ntest.facade.cart.CartFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/cart")
public class CartController {

    private final CartFacade cartFacade;

    @PostMapping("")
    public ResponseEntity createOrUpdateCart(@RequestParam Long productVariantId, @RequestParam(defaultValue = "1") int quantity) {
        cartFacade.addProductToCart(productVariantId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItemsDto>> findAllVariantsByCustomer() {
        return ResponseEntity.ok(cartFacade.findItemsByCart());
    }
}
