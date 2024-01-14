package com.ra1ntest.persistance.entity.product;

import com.ra1ntest.persistance.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_color")
public class ProductColor extends BaseEntity {

    @Column(nullable = false)
    private String color;

}
