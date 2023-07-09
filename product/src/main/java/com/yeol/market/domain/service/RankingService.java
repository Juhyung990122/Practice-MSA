package com.yeol.market.domain.service;

import com.yeol.market.common.exception.NotFoundProductExcepton;
import com.yeol.market.order.domain.repository.OrderRepository;
import com.yeol.market.product.application.dto.ProductResponse;
import com.yeol.market.domain.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public RankingService(final ProductRepository productRepository,
                          final OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<ProductResponse> findTop3Menu() {
        final List<String> top3ByCount = orderRepository.findTop3ByCount();
        return top3ByCount.stream()
                .map(menuUuid -> productRepository.findByUuId(menuUuid)
                        .orElseThrow(() -> new NotFoundProductExcepton("해당 메뉴를 찾을 수 없습니다")))
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
