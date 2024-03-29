package com.ra1ntest.persistance.entity.cart;

import com.ra1ntest.persistance.entity.BaseEntity;
import com.ra1ntest.persistance.entity.order.Order;
import com.ra1ntest.persistance.entity.user.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "carts")
public class Cart extends BaseEntity {


    @ManyToOne
    private Customer customer;

    private Boolean active;

    @OneToOne
    private Order order;

    private Long totalPrice;

    public Cart() {
        this.active = true;
    }
}
