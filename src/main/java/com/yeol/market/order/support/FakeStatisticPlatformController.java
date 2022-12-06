package com.yeol.market.order.support;

import com.yeol.market.order.domain.OrderHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FakeStatisticPlatformController {

    @PostMapping("/statistic")
    public void catchOrderHistory(final OrderHistory orderHistory) {
        log.info("send Completed!");
        log.info(orderHistory.toString());
    }
}
