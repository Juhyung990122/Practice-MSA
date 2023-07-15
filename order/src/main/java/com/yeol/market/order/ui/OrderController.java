package com.yeol.market.order.ui;

import com.yeol.market.order.application.OrderService;
import com.yeol.market.order.application.dto.OrderResponse;
import com.yeol.market.order.ui.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> orderProduct(@RequestBody final OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.order(orderRequest.getMemberId(), orderRequest.getMenuUuId()));
    }
}
