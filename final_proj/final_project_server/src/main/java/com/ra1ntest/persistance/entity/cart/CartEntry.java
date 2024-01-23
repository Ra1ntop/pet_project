package com.ra1ntest.persistance.entity.cart;

import com.ra1ntest.persistance.entity.BaseEntity;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

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

    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    public CartEntry() {
        this.quantity = 1;
    }
}
