package com.ra1ntest.api.dto.response.cart;

import com.ra1ntest.api.dto.response.ResponseDto;
import com.ra1ntest.exception.NonValidFieldDataException;
import com.ra1ntest.persistance.entity.cart.CartEntry;
import com.ra1ntest.persistance.entity.product.ProductImage;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;

@Getter
@Setter
public class CartItemsDto extends ResponseDto {
    private String name;
    private Integer quantity;
    private String image;
    private String price;
    private String totalPrice;
    private Integer ssd;
    private String color;
    private Long productVariantId;

    public CartItemsDto(CartEntry product) {
        setId(product.getProductVariant().getId());
        setQuantity(product.getQuantity());
        setName(product.getProductVariant().getProduct().getName());
        setSsd(product.getProductVariant().getSsd());
        setColor(product.getProductVariant().getProductColor().getColor());
        setPrice(String.valueOf(product.getPrice()));
        setTotalPrice(String.valueOf(product.getCart().getTotalPrice()));
        setProductVariantId(product.getProductVariant().getId());
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
