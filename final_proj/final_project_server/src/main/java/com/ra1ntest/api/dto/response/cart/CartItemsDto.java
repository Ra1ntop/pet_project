package com.ra1ntest.api.dto.response.cart;

import com.ra1ntest.api.dto.response.ResponseDto;
import com.ra1ntest.exception.NonValidFieldDataException;
import com.ra1ntest.persistance.entity.cart.CartEntry;
import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.persistance.entity.product.ProductImage;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CartItemsDto extends ResponseDto {
    private String name;
    private String image;
    private String price;
    private Integer ssd;
    private String color;

    public CartItemsDto(CartEntry product) {
        setId(product.getProductVariant().getId());
        setName(product.getProductVariant().getProduct().getName());
        setSsd(product.getProductVariant().getSsd());
        setColor(product.getProductVariant().getProductColor().getColor());
        setPrice(String.valueOf(product.getProductVariant().getPrice()));
        Set<ProductImage> images = product.getProductVariant().getProduct().getProductImages();
        if (CollectionUtils.isNotEmpty(images)) {
            ProductImage productImage = images
                    .stream()
                    .filter(image -> image.getMainImage())
                    .findFirst()
                    .orElseThrow(() -> new NonValidFieldDataException("Main image not found"));
            this.image = productImage.getImageUrl();
        }


    }
}