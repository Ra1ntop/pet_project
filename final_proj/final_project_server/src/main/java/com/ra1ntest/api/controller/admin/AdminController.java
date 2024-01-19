package com.ra1ntest.api.controller.admin;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @GetMapping("")
    public String get() {
        return "Admin get";
    }

    @GetMapping("/test")
    public String getTest() {
        return "Admin get Test";
    }

    @PostMapping()
    public String post() {
        return "Admin post";
    }

    @PostMapping("/test")
    public String postTest() {
        return "Admin post test";
    }

    @PutMapping
    public String put() {
        return "Admin put";
    }

    @DeleteMapping
    public String delete() {
        return "Admin delete";
    }

}
