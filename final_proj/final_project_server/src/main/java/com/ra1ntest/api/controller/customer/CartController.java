package com.ra1ntest.api.controller.customer;

import com.ra1ntest.facade.cart.CartFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/cart")
public class CartController {

    private final CartFacade cartFacade;

    @PostMapping("")
    public ResponseEntity<String> createOrUpdateCart(@RequestParam Long productVariantId, @RequestParam(defaultValue = "1") int quantity) {
        cartFacade.addProductToCart(productVariantId, quantity);
        return ResponseEntity.ok("Cart was created or updated");
    }
//    @GetMapping
//    public ResponseEntity<String> findAllVariantsByCustomer() {
//        return ResponseEntity.ok("Cart was created or updated");
//    }
}
