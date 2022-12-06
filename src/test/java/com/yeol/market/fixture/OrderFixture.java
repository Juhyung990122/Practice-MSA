package com.yeol.market.fixture;

import com.yeol.market.order.domain.Order;

public class OrderFixture {

    public static Order 주문_생성(final String menuUuid, final Long price) {
        return new Order(menuUuid, price);
    }
}
