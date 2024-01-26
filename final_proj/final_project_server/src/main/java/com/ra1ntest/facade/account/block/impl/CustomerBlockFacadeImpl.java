package com.ra1ntest.facade.account.block.impl;

import com.ra1ntest.api.dto.request.panel.BlockCustomerDto;
import com.ra1ntest.facade.account.block.CustomerBlockFacade;
import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.repository.user.CustomerRepository;
import com.ra1ntest.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerBlockFacadeImpl implements CustomerBlockFacade {

    private final CustomerRepository customerRepository;

    @Override
    public void blockCustomer(BlockCustomerDto blockCustomerDto) {
        if (customerRepository.existsCustomerByLoginAndEnabledTrueAndAccountNonLockedTrue(blockCustomerDto.getCustomerMail())) {
            Customer customer = customerRepository.findCustomerByLoginAndEnabledTrue(blockCustomerDto.getCustomerMail());
            customer.setAccountNonLocked(false);
            customer.setDescription("Account was blocked by " + SecurityUtil.getUserName() + " ." + " With reason: " + blockCustomerDto.getReason());
            LocalDateTime currentDateTime = LocalDateTime.now();
            customer.setBlockedAt(String.valueOf(currentDateTime));

            System.out.println(customer.toString());
            System.out.println(customer.toString());
            customerRepository.save(customer);
        }
    }
}
