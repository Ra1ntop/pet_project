package com.ra1ntest.api.controller.customer;

import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.api.dto.response.order.OrderDto;
import com.ra1ntest.facade.order.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/order")
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping
    public ResponseEntity<OrderDto> findAllVariantsByCustomer() {
        OrderDto orderDtos = orderFacade.findOrder();
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping()
    public ResponseEntity<String> createOrder() {
        orderFacade.createOrder();
        return ResponseEntity.status(201).build();
    }
}
