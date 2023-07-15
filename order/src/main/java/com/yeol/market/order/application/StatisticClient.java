package com.yeol.market.order.application;

import com.yeol.market.order.domain.OrderHistory;

public interface StatisticClient {

    void sendOrderHistory(final OrderHistory orderHistory);
}
