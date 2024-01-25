package com.ra1ntest.api.controller.admin;

import com.ra1ntest.api.dto.response.account.CustomersDto;
import com.ra1ntest.facade.account.AccountFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AccountFacade accountFacade;

    @GetMapping("/panel")
    public ResponseEntity<List<CustomersDto>> get() {
        System.out.println(accountFacade.getCustomers());
        return ResponseEntity.ok(accountFacade.getCustomers());
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
