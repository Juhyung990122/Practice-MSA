package com.yeol.market.order.ui;

import com.yeol.market.order.application.OrderService;
import com.yeol.market.order.application.dto.OrderResponse;
import com.yeol.market.order.ui.dto.OrderRequest;
import com.yeol.market.product.application.ProductService;
import com.yeol.market.product.application.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(final OrderService orderService,
                           final ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> orderProduct(@RequestBody final OrderRequest orderRequest) {
        final ProductResponse menu = productService.getProduct(orderRequest.getMenuUuId());
        return ResponseEntity.ok(orderService.order(orderRequest.getMemberId(), menu));
    }
}
