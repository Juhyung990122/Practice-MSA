package com.yeol.market.order.domain.repository;

import java.util.List;

public interface OrderRepositoryCustom {
    List<String> findTop3ByCount();
}
