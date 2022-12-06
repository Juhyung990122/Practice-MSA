package com.yeol.market.order.domain.repository;

import static com.yeol.market.order.domain.QOrder.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yeol.market.order.domain.QOrder;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

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
