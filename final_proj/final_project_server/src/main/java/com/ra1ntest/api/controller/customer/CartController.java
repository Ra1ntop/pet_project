package com.ra1ntest.api.controller.customer;

import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.facade.cart.CartFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/cart")
public class CartController {

    private final CartFacade cartFacade;

    @PostMapping("")
    public ResponseEntity<String> createOrUpdateCart(@RequestParam Long productVariantId, @RequestParam(defaultValue = "1") int quantity) {
        System.out.println("quantity = " + quantity);
        cartFacade.addProductToCart(productVariantId, quantity);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<CartItemsDto>> findAllVariantsByCustomer() {
        return ResponseEntity.ok(cartFacade.findItemsByCart());
    }

    @PutMapping()
    public ResponseEntity<String> updateQuantityInCart(@RequestParam Long productVariantId, @RequestParam(defaultValue = "1") int quantity) {
        cartFacade.updateQuantityInCart(productVariantId, quantity);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteItemInCart(@RequestParam Long productVariantId) {
        cartFacade.deleteProductFromCart(productVariantId);
        return ResponseEntity.ok().build();
    }
}
