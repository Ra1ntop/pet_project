package com.ra1ntest.persistance.entity.order;

import com.ra1ntest.persistance.entity.BaseEntity;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.persistance.type.OrderStatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;


    public Order() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.createdAt = String.valueOf(currentDateTime);
        this.orderId = String.valueOf(UUID.randomUUID());
        this.orderStatus = OrderStatusType.CONFIRMING.getOrderStatus();
    }
}
