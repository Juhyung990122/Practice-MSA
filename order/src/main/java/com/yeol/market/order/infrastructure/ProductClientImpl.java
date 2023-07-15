package com.yeol.market.order.infrastructure;

import com.yeol.market.order.application.ProductClient;
import com.yeol.market.order.application.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class ProductClientImpl implements ProductClient {
    private final String productUrl;

    public ProductClientImpl(@Value("${external.api.placeHolder}") final String productUrl){
        this.productUrl = productUrl;
    }

    @Override
    public ProductResponseDto getProductByUUID(String menuUuId) {
        final WebClient webClient = initWebclient();
        return webClient.get()
                .uri('/' + menuUuId)
                .retrieve()
                .bodyToMono(ProductResponseDto.class)
                .block();
    }

    private WebClient initWebclient() {
        return WebClient.builder()
                .baseUrl(productUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
