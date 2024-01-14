package com.ra1ntest.service.product.impl;

import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import com.ra1ntest.repository.product.ProductVariantRepository;
import com.ra1ntest.service.product.ProductService;
import com.ra1ntest.service.product.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    @Override
    public List<ProductVariant> findByProduct(Product product) {
        return productVariantRepository.findAllByProduct(product);
    }
}
