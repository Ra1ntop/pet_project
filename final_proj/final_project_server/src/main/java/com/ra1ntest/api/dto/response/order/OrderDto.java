package com.ra1ntest.api.dto.response.order;

import com.ra1ntest.api.dto.response.ResponseDto;
import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.api.dto.response.product.ProductVariantDto;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.cart.CartEntry;
import com.ra1ntest.persistance.entity.order.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto extends ResponseDto {

    private String orderId;
    private List<CartItemsDto> cartEntry;
    private BigDecimal price;
    private String orderStatus;

    public OrderDto(Order order, List<CartItemsDto> cartEntries) {
        setId(order.getId());
        setOrderId(order.getOrderId());
        setCartEntry(cartEntries);
        setPrice(order.getPrice());
        setOrderStatus(order.getOrderStatus());
    }
}
