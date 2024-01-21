package com.ra1ntest.api.dto.response.cart;

import com.ra1ntest.api.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CartDto extends ResponseDto {

    private Boolean active;
    private Integer totalQuantity;
    private Integer totalPrice;
    private List<CartEntryDto> entries;


}
