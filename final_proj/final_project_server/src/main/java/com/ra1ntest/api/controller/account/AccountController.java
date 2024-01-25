package com.ra1ntest.api.controller.account;

import com.ra1ntest.api.dto.response.account.AccountDto;
import com.ra1ntest.facade.account.AccountFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/account")
public class AccountController {

    private final AccountFacade accountFacade;

    @GetMapping
    public ResponseEntity<AccountDto> findAllInfoByAccount() {
        return ResponseEntity.ok(accountFacade.getUserInfo());
    }
}
