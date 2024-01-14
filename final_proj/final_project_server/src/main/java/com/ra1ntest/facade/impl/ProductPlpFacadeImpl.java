package com.ra1ntest.facade.impl;

import com.ra1ntest.api.dto.response.product.ProductPlpDto;
import com.ra1ntest.facade.ProductPlpFacade;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import com.ra1ntest.service.product.ProductService;
import com.ra1ntest.service.product.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductPlpFacadeImpl implements ProductPlpFacade {

    private final ProductService productService;
    private final ProductVariantService productVariantService;

    @Override
    public List<ProductPlpDto> findAll() {
        return productService.findAll()
                .stream()
                .map(product -> {
                    List<ProductVariant> variants = productVariantService.findByProduct(product);
                    return new ProductPlpDto(product, variants);
                }).toList();
    }
}
