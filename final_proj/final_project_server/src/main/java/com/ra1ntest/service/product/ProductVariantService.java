package com.ra1ntest.service.product;

import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.persistance.entity.product.ProductVariant;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariant> findByProduct(Product product);
}
