package com.yeol.market.point.ui.dto;

import com.yeol.market.point.application.dto.PointChargeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PointChargeRequest {

    private Long memberId;
    private Long chargePrice;

    public PointChargeDto toServiceDto() {
        return new PointChargeDto(memberId, chargePrice);
    }
}
