package com.ra1ntest.api.controller.customer;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController {
    @GetMapping
    public String get() {
        return "customer get";
    }

    @PostMapping
    public String post() {
        return "customer post";
    }

    @PutMapping
    public String put() {
        return "customer put";
    }

    @DeleteMapping
    public String delete() {
        return "customer delete";
    }
}
