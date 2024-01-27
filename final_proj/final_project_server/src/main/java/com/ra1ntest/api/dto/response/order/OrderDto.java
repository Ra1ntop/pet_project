package com.ra1ntest.api.dto.response.order;

import com.ra1ntest.api.dto.response.ResponseDto;
import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.persistance.entity.order.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderDto extends ResponseDto {

    private String orderId;
    private BigDecimal price;
    private List<CartItemsDto> cartEntry;
    private String orderStatus;
    private String createdAt;
    private String updatedAt;


    public OrderDto(Order order, List<CartItemsDto> cartEntries) {
        setId(order.getId());
        setOrderId(order.getOrderId());
        setCartEntry(cartEntries);
        setPrice(order.getPrice());
        setOrderStatus(order.getOrderStatus());
        setCreatedAt(order.getCreatedAt());
        setUpdatedAt(order.getUpdatedAt());
    }
}
