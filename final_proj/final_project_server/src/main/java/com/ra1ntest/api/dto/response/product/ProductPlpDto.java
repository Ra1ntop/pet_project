package com.ra1ntest.api.dto.response.product;

import com.ra1ntest.api.dto.response.ResponseDto;
import com.ra1ntest.exception.NonValidFieldDataException;
import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.persistance.entity.product.ProductImage;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class ProductPlpDto extends ResponseDto {

    private String name;
    private String image;
    private String minPrice;
    private String maxPrice;

    public ProductPlpDto(Product product, List<ProductVariant> productVariants) {
        setId(product.getId());
        setName(product.getName());
        Set<ProductImage> images = product.getProductImages();
        if (CollectionUtils.isNotEmpty(images)) {
            ProductImage productImage = images
                    .stream()
                    .filter(image -> image.getMainImage())
                    .findFirst()
                    .orElseThrow(() -> new NonValidFieldDataException("Main image not found"));
            this.image = productImage.getImageUrl();
        }
        if (CollectionUtils.isEmpty(productVariants)) {
            throw new NonValidFieldDataException("Product variants is not present");
        }
        ProductVariant max = productVariants.stream().max(ProductVariant::compareTo).get();
        ProductVariant min = productVariants.stream().min(ProductVariant::compareTo).get();
        this.maxPrice = max.getPrice().toString();
        this.minPrice = min.getPrice().toString();


    }

}
