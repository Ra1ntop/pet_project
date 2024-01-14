package com.ra1ntest.facade;

import com.ra1ntest.api.dto.response.product.ProductPdpDto;
import com.ra1ntest.api.dto.response.product.ProductVariantDto;

import java.util.List;

public interface ProductPdpFacade {

    boolean isProductInProductIdExist(Long productId);
    ProductPdpDto findAllByProduct(Long productId);
}
