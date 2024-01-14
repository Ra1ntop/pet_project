package com.ra1ntest.persistance.entity.product;

import com.ra1ntest.persistance.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 4096)
    private String description;


    @Column(nullable = false)
    private String sizeScreen;

    @ManyToMany
    @JoinTable(
            name = "thumbnails",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_image")
    )
    private Set<ProductImage> productImages;

    public Product() {
        this.productImages = new HashSet<>();

    }
}
