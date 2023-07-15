package com.yeol.market.point.fixture;


import com.yeol.market.domain.Point;

public class PointFixture {

    public static Long 멤버1_ID = 1L;
    public static Long 멤버2_ID = 2L;
    public static Long 멤버3_ID = 3L;

    public static Point 포인트_생성(final Long memberId, final Long balance) {
        return new Point(memberId, balance);
    }
}
