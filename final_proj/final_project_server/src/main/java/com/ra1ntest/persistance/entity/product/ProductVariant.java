package com.ra1ntest.persistance.entity.product;

import com.ra1ntest.persistance.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_variant")
public class ProductVariant extends BaseEntity implements Comparable<ProductVariant> {

    @Column(nullable = false)
    private String cpu;

    @Column(nullable = false)
    private Integer ram;

    @Column(nullable = false)
    private Integer ssd;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String camera;

    @Column(nullable = false)
    private String safety;

    @Column(nullable = false)
    private String battery;

    @Column(nullable = false)
    private String os;

    @Column(nullable = false)
    private String simCard;

    @Column(nullable = false)
    private String sensors;

    @Column(nullable = false)
    private String waterResistance;

    @Column(nullable = false)
    private String videoRecording;

    @ManyToOne
    private ProductColor productColor;

    @ManyToOne
    private Product product;

    @Override
    public int compareTo(ProductVariant o) {
        return price.compareTo(o.price);
    }
}
