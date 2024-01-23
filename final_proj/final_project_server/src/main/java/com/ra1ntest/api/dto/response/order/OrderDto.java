package com.ra1ntest.api.dto.response.order;

import com.ra1ntest.api.dto.response.ResponseDto;
import com.ra1ntest.persistance.entity.cart.Cart;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDto extends ResponseDto {

    private String orderId;
    private Cart cart;
    private BigDecimal price;
    private String orderStatus;

    public OrderDto() {

    }
}
