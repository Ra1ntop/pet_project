package com.ra1ntest.api.dto.request.panel;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangeOrderDto {
    private String status;
    private Long id;

}
