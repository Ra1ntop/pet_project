package com.ra1ntest.api.controller.customer;

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
    public ResponseEntity<List<OrderDto>> findAllVariantsByCustomer() {
        List<OrderDto> orderDtos = orderFacade.findOrders();
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping()
    public ResponseEntity<String> createOrder() {
        orderFacade.createOrder();
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/cancel-order")
    public ResponseEntity<String> cancelOrder(@RequestParam Long orderId) {
        System.out.println("orderId = " + orderId);
        orderFacade.cancelOrder(orderId);
        return ResponseEntity.status(201).build();
    }
}
