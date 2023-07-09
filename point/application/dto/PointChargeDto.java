package com.yeol.market.point.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PointChargeDto {

    private Long memberId;
    private Long chargePrice;
}
