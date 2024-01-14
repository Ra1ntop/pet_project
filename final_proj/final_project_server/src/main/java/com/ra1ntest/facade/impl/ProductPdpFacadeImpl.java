package com.ra1ntest.facade.impl;

import com.ra1ntest.api.dto.response.product.ProductPdpDto;
import com.ra1ntest.api.dto.response.product.ProductVariantDto;
import com.ra1ntest.facade.ProductPdpFacade;
import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import com.ra1ntest.service.product.ProductService;
import com.ra1ntest.service.product.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductPdpFacadeImpl implements ProductPdpFacade {

    private final ProductVariantService productVariantService;
    private final ProductService productService;


    @Override
    public boolean isProductInProductIdExist(Long productId) {
        return productService.isProductExists(productId);
    }

    @Override
    public ProductPdpDto findAllByProduct(Long productId) {
        Product product = productService.findById(productId);
        List<ProductVariant> variants = productVariantService.findByProduct(product);
        return new ProductPdpDto(product, variants);
    }
}
