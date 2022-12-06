package com.yeol.market.acceptance;


import static com.yeol.market.fixture.PointFixture.멤버1_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.yeol.market.order.domain.Order;
import com.yeol.market.product.application.dto.ProductResponse;
import com.yeol.market.utils.AcceptanceTest;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("메뉴 목록을 조회한다")
    void getMenus() {
        final var response = 상품_목록_조회_요청();
        final var menus = response.jsonPath().getList(".", ProductResponse.class).stream()
                .map(ProductResponse::getName)
                .collect(Collectors.toList());

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(menus)
                        .contains(
                                "감자",
                                "당근",
                                "대파",
                                "양배추",
                                "양파"
                        )
        );
    }

    @Test
    @DisplayName("최근 7일 동안의 인기 메뉴 3개 조회한다.")
    void getTop3MenuUntil7days() {
        다중_주문_결제_요청(멤버1_ID, 당근_UUID, 5);
        다중_주문_결제_요청(멤버1_ID, 대파_UUID, 4);
        다중_주문_결제_요청(멤버1_ID, 감자_UUID, 3);
        다중_주문_결제_요청(멤버1_ID, 양배추_UUID, 2);

        final List<Order> top3ByCount = orderRepository.findAll();

        final var response = 상위_3개_상품_조회_요청();
        final var menus = response.jsonPath().getList(".", ProductResponse.class).stream()
                .map(ProductResponse::getName)
                .collect(Collectors.toList());

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(menus)
                        .contains(
                                "당근",
                                "대파",
                                "감자"
                        )
        );
    }
}
