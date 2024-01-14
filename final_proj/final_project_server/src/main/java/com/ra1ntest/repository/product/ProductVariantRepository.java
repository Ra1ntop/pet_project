package com.ra1ntest.repository.product;

import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import com.ra1ntest.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductVariantRepository extends BaseRepository<ProductVariant> {
    List<ProductVariant> findAllByProduct(Product product);
}
