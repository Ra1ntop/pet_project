package com.ra1ntest.facade.order;

import com.ra1ntest.api.dto.request.panel.ChangeOrderDto;
import com.ra1ntest.api.dto.response.order.OrderDto;
import com.ra1ntest.persistance.entity.cart.Cart;

import java.util.List;

public interface OrderFacade {
    void createOrder();

    OrderDto findOrder(Cart cart);

    List<OrderDto> findOrders();

    List<OrderDto> findOrdersByCustomerId(Long customerId);


    void cancelOrder(Long orderId);

    void changeOrderStatus(ChangeOrderDto changeOrderDto);
}
