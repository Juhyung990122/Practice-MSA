package com.yeol.market.order.utils;

import com.yeol.market.OrderApplication;
import com.yeol.market.order.config.DatabaseCleaner;
import com.yeol.market.order.application.OrderService;
import com.yeol.market.order.domain.Order;
import com.yeol.market.order.domain.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OrderApplication.class)
public class ServiceTest {


    @Autowired
    protected OrderService orderService;
    @Autowired
    protected OrderRepository orderRepository;
    @Autowired
    protected DatabaseCleaner databaseCleaner;

    protected Order 주문내역_등록(final Order order) {
        return orderRepository.save(order);
    }

    @AfterEach
    void tearDown() {
        databaseCleaner.clear();
    }
}
