package com.ra1ntest.persistance.entity.cart;

import com.ra1ntest.persistance.entity.BaseEntity;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "cart_entries")
public class CartEntry extends BaseEntity {

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private ProductVariant productVariant;


    private Integer quantity;

    public CartEntry() {
        this.quantity = 1;
    }
}
