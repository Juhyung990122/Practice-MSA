package com.yeol.market.order.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.yeol.market.order.domain.QOrder.order;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<String> findTop3ByCount() {
        final LocalDateTime now = LocalDateTime.now();
        return jpaQueryFactory.select(order.productUuid)
                .from(order)
                .where(order.createdTime.between(now.minusDays(7), now))
                .groupBy(order.productUuid)
                .orderBy(order.productUuid.count().desc())
                .limit(3)
                .fetch();
    }
}
