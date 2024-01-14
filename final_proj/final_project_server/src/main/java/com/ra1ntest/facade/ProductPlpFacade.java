package com.ra1ntest.facade;

import com.ra1ntest.api.dto.response.product.ProductPlpDto;

import java.util.List;

public interface ProductPlpFacade {
    List<ProductPlpDto> findAll();
}
