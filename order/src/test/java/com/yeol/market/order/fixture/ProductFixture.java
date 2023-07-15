package com.yeol.market.order.fixture;


import com.yeol.market.order.application.dto.ProductResponseDto;

public class ProductFixture {

    public static ProductResponseDto 당근 = new ProductResponseDto("1111","당근", 3_000L);
    public static ProductResponseDto 대파 = new ProductResponseDto("1112","대파", 3_500L);
    public static ProductResponseDto 양배추 = new ProductResponseDto("1113","양배추", 4_000L);
    public static ProductResponseDto 양파 = new ProductResponseDto("1114","양파", 4_500L);
    public static ProductResponseDto 감자 = new ProductResponseDto("1115","감자", 5_000L);

    public static ProductResponseDto 메뉴_생성(final String uuid, final String name, final Long price) {
        return new ProductResponseDto(uuid, name, price);
    }
}
