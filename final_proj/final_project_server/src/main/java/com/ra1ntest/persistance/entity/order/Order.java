package com.ra1ntest.persistance.entity.order;

import com.ra1ntest.persistance.entity.BaseEntity;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.persistance.type.OrderStatusType;
import com.ra1ntest.persistance.type.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    private Customer customer;

    @Column(nullable = false, unique = true, name = "orderid")
    private String orderId;
    @OneToOne
    private Cart cart;

    @Column(nullable = false, name = "order_status")
    private String orderStatus;

    private BigDecimal price;

    public Order() {
        this.orderId = String.valueOf(UUID.randomUUID());
        this.orderStatus = OrderStatusType.CONFIRMING.getOrderStatus();
    }
}
