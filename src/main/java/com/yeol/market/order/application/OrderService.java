package com.yeol.market.order.application;

import com.yeol.market.order.application.dto.OrderResponse;
import com.yeol.market.order.application.event.PayEvent;
import com.yeol.market.order.domain.Order;
import com.yeol.market.order.domain.OrderHistory;
import com.yeol.market.order.domain.repository.OrderRepository;
import com.yeol.market.order.support.StatisticClient;
import com.yeol.market.product.application.dto.ProductResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderRepository orderRepository;
    private final StatisticClient statisticClient;

    public OrderService(final ApplicationEventPublisher applicationEventPublisher,
                        final OrderRepository orderRepositry,
                        final StatisticClient statisticClient) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.orderRepository = orderRepositry;
        this.statisticClient = statisticClient;
    }

    public OrderResponse order(final Long memberId, final ProductResponse product) {
        final Order order = new Order(memberId, product.getUuid(), product.getPrice());
        applicationEventPublisher.publishEvent(new PayEvent(memberId, product.getPrice()));
        statisticClient.sendOrderHistory(new OrderHistory(memberId, product.getUuid(), product.getPrice()));
        orderRepository.save(order);
        return OrderResponse.of(order);
    }
}
