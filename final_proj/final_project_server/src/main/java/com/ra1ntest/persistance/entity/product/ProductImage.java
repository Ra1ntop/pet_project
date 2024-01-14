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
@Table(name = "product_image")
public class ProductImage extends BaseEntity {

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "main_image", nullable = false)
    private Boolean mainImage;

    @ManyToMany(mappedBy = "productImages")
    private Set<Product> products;

    public ProductImage() {
        this.products = new HashSet<>();
        this.mainImage = false;
    }
}
