package com.ra1ntest.facade.account.impl;

import com.ra1ntest.api.dto.response.account.AccountDto;
import com.ra1ntest.api.dto.response.account.CustomersDto;
import com.ra1ntest.facade.account.AccountFacade;
import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.persistance.entity.user.User;
import com.ra1ntest.service.account.AccountService;
import com.ra1ntest.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;

    @Override
    public AccountDto getUserInfo() {
        String userName = SecurityUtil.getUserName();
        User user = accountService.getUserInfo(userName);
        return new AccountDto(user);
    }


    @Override
    public List<CustomersDto> getCustomers() {

        List<Customer> customers = accountService.getCustomers();

        List<CustomersDto> customersDtoList = customers.stream()
                .map(CustomersDto::new)
                .toList();

        return customersDtoList;

    }
}
