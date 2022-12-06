package com.yeol.market.point.application.dto;

import com.yeol.market.point.domain.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PointResponse {

    private Long memberId;
    private Long balance;

    public static PointResponse of(final Point point) {
        return new PointResponse(point.getMemberId(), point.getBalance());
    }
}
