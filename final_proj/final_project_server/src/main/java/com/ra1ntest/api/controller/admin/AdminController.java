package com.ra1ntest.api.controller.admin;

import com.ra1ntest.api.dto.request.auth.RegisterDto;
import com.ra1ntest.api.dto.request.panel.BlockCustomerDto;
import com.ra1ntest.api.dto.response.account.CustomersDto;
import com.ra1ntest.facade.account.AccountFacade;
import com.ra1ntest.facade.account.block.CustomerBlockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AccountFacade accountFacade;
    private final CustomerBlockFacade customerBlockFacade;

    @GetMapping("/panel")
    public ResponseEntity<List<CustomersDto>> get() {
        System.out.println(accountFacade.getCustomers());
        return ResponseEntity.ok(accountFacade.getCustomers());
    }

    @PutMapping("/block-user")
    public String put(@RequestBody BlockCustomerDto blockCustomerDto) {
        System.out.println(blockCustomerDto.toString());
        customerBlockFacade.blockCustomer(blockCustomerDto);
        return "dwdw";
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


    @DeleteMapping
    public String delete() {
        return "Admin delete";
    }

}
