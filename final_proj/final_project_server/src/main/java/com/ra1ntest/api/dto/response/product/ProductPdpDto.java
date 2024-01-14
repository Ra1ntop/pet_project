package com.ra1ntest.api.dto.response.product;

import com.ra1ntest.api.dto.response.ResponseDto;
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
public class ProductPdpDto extends ResponseDto {

    private String cpu;
    private String name;
    private String description;
    private Integer ram;
    private String sizeScreen;
    private String camera;
    private String safety;
    private String battery;
    private String os;
    private String simCard;
    private String sensors;
    private String waterResistance;
    private String videoRecording;
    private List<String> images;
    private List<ProductVariantDto> variantDtos;

    public ProductPdpDto(Product product, List<ProductVariant> productVariants) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setSizeScreen(product.getSizeScreen());
        Set<ProductImage> images = product.getProductImages();
        if (CollectionUtils.isNotEmpty(images)) {
            this.images = images
                    .stream()
                    .map(ProductImage::getImageUrl)
                    .toList();
        }
        if (CollectionUtils.isNotEmpty(productVariants)) {
            setRam(productVariants.stream().findFirst().get().getRam());
            setCpu(productVariants.stream().findFirst().get().getCpu());
            setCamera(productVariants.stream().findFirst().get().getCamera());
            setSafety(productVariants.stream().findFirst().get().getSafety());
            setBattery(productVariants.stream().findFirst().get().getBattery());
            setOs(productVariants.stream().findFirst().get().getOs());
            setSimCard(productVariants.stream().findFirst().get().getSimCard());
            setSensors(productVariants.stream().findFirst().get().getSensors());
            setWaterResistance(productVariants.stream().findFirst().get().getWaterResistance());
            setVideoRecording(productVariants.stream().findFirst().get().getVideoRecording());
            this.variantDtos = productVariants.stream().map(ProductVariantDto::new).toList();
        }
    }

}
