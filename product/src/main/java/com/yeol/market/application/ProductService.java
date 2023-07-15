package com.yeol.market.application;

import java.util.List;
import java.util.stream.Collectors;

import com.coffeeshop.exception.NotFoundProductExcepton;
import com.yeol.market.application.dto.ProductResponse;
import com.yeol.market.domain.Product;
import com.yeol.market.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public ProductResponse getProduct(final String uuid) {
        final Product product = productRepository.findByUuId(uuid)
                .orElseThrow(() -> new NotFoundProductExcepton("해당 상품을 찾을 수 없습니다."));
        return ProductResponse.of(product);
    }
}
