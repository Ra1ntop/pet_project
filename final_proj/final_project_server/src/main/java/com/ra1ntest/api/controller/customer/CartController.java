package com.ra1ntest.api.controller.customer;

import com.ra1ntest.facade.cart.CartFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/cart")
public class CartController {

    private final CartFacade cartFacade;

    @PostMapping
    public ResponseEntity<String> createOrUpdateCart(@RequestParam Long productVariantId, @RequestParam(defaultValue = "1") int quantity) {
        cartFacade.addProductToCart(productVariantId, quantity);
        return ResponseEntity.ok("Cart was created or updated");
    }
}
