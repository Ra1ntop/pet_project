package com.ra1ntest.facade.account;

import com.ra1ntest.api.dto.response.account.AccountDto;
import com.ra1ntest.api.dto.response.account.CustomersDto;

import java.util.List;

public interface AccountFacade {
    AccountDto getUserInfo();

    List<CustomersDto> getCustomers();
}
