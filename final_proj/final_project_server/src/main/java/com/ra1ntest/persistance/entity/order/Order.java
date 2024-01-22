package com.ra1ntest.persistance.entity.order;

import com.ra1ntest.persistance.entity.BaseEntity;
import com.ra1ntest.persistance.entity.cart.Cart;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    @OneToOne
    private Cart cart;


}
