package com.ra1ntest.facade.order;

import com.ra1ntest.api.dto.response.order.OrderDto;

import java.util.List;

public interface OrderFacade {
    void createOrder();

    List<OrderDto> findOrders();

}
