package com.yeol.market.order.fixture;


import com.yeol.market.order.domain.Order;

public class OrderFixture {


    public static Long 멤버1_ID = 1L;
    public static Long 멤버2_ID = 2L;
    public static Long 멤버3_ID = 3L;


    public static Order 주문_생성(final String menuUuid, final Long price) {
        return new Order(menuUuid, price);
    }
}
