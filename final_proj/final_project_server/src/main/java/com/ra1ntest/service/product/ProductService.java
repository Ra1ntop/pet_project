package com.ra1ntest.service.product;

import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.service.CrudService;
import com.ra1ntest.service.FindAllService;

public interface ProductService extends CrudService<Product>, FindAllService<Product> {
    boolean isProductExists(Long id);

}
