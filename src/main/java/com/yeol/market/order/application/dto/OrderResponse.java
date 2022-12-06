package com.yeol.market.order.application.dto;

import com.yeol.market.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String menuUuid;

    private Long paymentPrice;

    public static OrderResponse of(final Order order) {
        return new OrderResponse(order.getProductUuid(), order.getPaymentPrice());
    }
}
