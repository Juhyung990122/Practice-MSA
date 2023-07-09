package com.yeol.market.order.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PayEvent {

    private final Long memberId;
    private final Long paymentPrice;
}
