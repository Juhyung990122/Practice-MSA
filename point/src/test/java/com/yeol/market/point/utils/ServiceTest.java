package com.yeol.market.point.utils;

import com.yeol.market.config.DatabaseCleaner;
import com.yeol.market.config.DatabaseCleanerConfig;
import com.yeol.market.order.application.OrderService;
import com.yeol.market.order.domain.Order;
import com.yeol.market.order.domain.repository.OrderRepository;
import com.yeol.market.point.application.PointService;
import com.yeol.market.point.domain.Point;
import com.yeol.market.point.domain.repository.PointRepository;
import com.yeol.market.product.application.ProductService;
import com.yeol.market.product.domain.Product;
import com.yeol.market.product.domain.repository.ProductRepository;
import com.yeol.market.product.domain.service.RankingService;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(value = {DatabaseCleanerConfig.class})
public class ServiceTest {

    @Autowired
    protected ProductService productService;

    @Autowired
    protected PointService pointService;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected RankingService rankingService;

    @Autowired
    protected PointRepository pointRepository;


    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected DatabaseCleaner databaseCleaner;

    protected Product 상품_등록(final Product product) {
        return productRepository.save(product);
    }

    protected Point 포인트_등록(final Point point) {
        return pointRepository.save(point);
    }

    protected Order 주문내역_등록(final Order order) {
        return orderRepository.save(order);
    }

    @AfterEach
    void tearDown() {
        databaseCleaner.execute();
    }
}
