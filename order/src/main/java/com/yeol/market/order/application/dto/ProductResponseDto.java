package com.yeol.market.order.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponseDto {

    private String uuid;
    private String name;
    private Long price;
}
