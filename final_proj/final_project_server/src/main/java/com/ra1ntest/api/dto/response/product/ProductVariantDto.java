package com.ra1ntest.api.dto.response.product;

import com.ra1ntest.api.dto.response.ResponseDto;
import com.ra1ntest.persistance.entity.product.ProductColor;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductVariantDto extends ResponseDto {
    private Integer ssd;
    private BigDecimal price;
    private String productColor;

    public ProductVariantDto(ProductVariant productVariant) {
        setId(productVariant.getId());
        setSsd(productVariant.getSsd());
        setPrice(productVariant.getPrice());
        ProductColor productColor1 = productVariant.getProductColor();
        if (productColor1 != null) {
            this.productColor = productColor1.getColor();
        }
    }

}
