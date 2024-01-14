package com.ra1ntest.api.controller;

import com.ra1ntest.api.dto.response.product.ProductPdpDto;
import com.ra1ntest.api.dto.response.product.ProductPlpDto;
import com.ra1ntest.facade.ProductPdpFacade;
import com.ra1ntest.facade.ProductPlpFacade;
import com.ra1ntest.service.product.ProductService;
import com.ra1ntest.service.product.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductPlpFacade productPlpFacade;
    private final ProductPdpFacade productPdpFacade;
    @GetMapping("/plp")
    public ResponseEntity<List<ProductPlpDto>> findAll() {
        return ResponseEntity.ok(productPlpFacade.findAll());
    }

    @GetMapping("/pdp/{productId}")
    public ResponseEntity<ProductPdpDto> findAllVariantsByProduct(@PathVariable Long productId) {
        if (!productPdpFacade.isProductInProductIdExist(productId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productPdpFacade.findAllByProduct(productId));
    }
}
