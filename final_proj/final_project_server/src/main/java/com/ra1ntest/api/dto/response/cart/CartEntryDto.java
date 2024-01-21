package com.ra1ntest.api.dto.response.cart;

import com.ra1ntest.api.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartEntryDto extends ResponseDto {


    private String name;
    private String image;
    private Integer quantity;
    private Integer ssd;
    private String productColor;
    private BigDecimal price;


}
