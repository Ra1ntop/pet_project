package com.ra1ntest.api.controller.admin;

import com.ra1ntest.api.dto.request.panel.BlockCustomerDto;
import com.ra1ntest.api.dto.request.panel.ChangeOrderDto;
import com.ra1ntest.api.dto.response.account.CustomersDto;
import com.ra1ntest.api.dto.response.order.OrderDto;
import com.ra1ntest.facade.account.AccountFacade;
import com.ra1ntest.facade.account.block.CustomerBlockFacade;
import com.ra1ntest.facade.order.OrderFacade;
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
    private final OrderFacade orderFacade;

    @GetMapping("/panel")
    public ResponseEntity<List<CustomersDto>> get() {
        return ResponseEntity.ok(accountFacade.getCustomers());
    }

    @PutMapping("/block-user")
    public ResponseEntity<String> put(@RequestBody BlockCustomerDto blockCustomerDto) {
        System.out.println(blockCustomerDto.toString());
        customerBlockFacade.blockCustomer(blockCustomerDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer-orders/{customerId}")
    public ResponseEntity<List<OrderDto>> findAllVariantsByProduct(@PathVariable("customerId") Long customerId) {
        List<OrderDto> orderDtos = orderFacade.findOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orderDtos);
    }

    @PutMapping("/change-order")
    public ResponseEntity<String> changeOrder(@RequestBody ChangeOrderDto changeOrderDto) {
        orderFacade.changeOrderStatus(changeOrderDto);
        return ResponseEntity.ok().build();
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
