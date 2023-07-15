package com.yeol.market.order.infrastructure;

import com.yeol.market.order.application.StatisticClient;
import com.yeol.market.order.domain.OrderHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StatisticClientImpl implements StatisticClient {

    private final String statisticUrl;

    public StatisticClientImpl(@Value("${external.api.placeHolder}") String statisticUrl) {
        this.statisticUrl = statisticUrl;
    }

    public void sendOrderHistory(final OrderHistory orderHistory) {
        WebClient webClient = initWebclient();
        webClient.post().uri("/statistic")
                .bodyValue(orderHistory)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    private WebClient initWebclient() {
        return WebClient.builder()
                .baseUrl(statisticUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
