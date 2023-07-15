package com.yeol.market.order.acceptance;

import static com.yeol.market.order.fixture.OrderFixture.멤버2_ID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.yeol.market.order.utils.AcceptanceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("주문을 한다.")
    void orderSuccess() {
        final var response = 주문_결제_요청(멤버2_ID, 감자_UUID);

        assertAll(
                () -> Assertions.assertThat(response.statusCode()).isEqualTo(200),
                () -> verify(fakeStatisticPlatformController, atLeastOnce()).catchOrderHistory(any())
        );
    }

    @Test
    @DisplayName("잔액이 부족해서 결제에 실패하면 400 을 반환한다.")
    void orderFailSoExpensive() {
        다중_주문_결제_요청(멤버2_ID, 감자_UUID, 2);
        final var response = 주문_결제_요청(멤버2_ID, 당근_UUID);

        assertAll(
                () -> Assertions.assertThat(response.statusCode()).isEqualTo(400)
        );
    }

    @Test
    @DisplayName("존재하지 않는 사용자가 주문할 경우 404 를 반환한다.")
    void orderNotExistMember() {
        final var response = 주문_결제_요청(9999L, 감자_UUID);

        Assertions.assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    @DisplayName("주문한 메뉴가 존재하지 않는다면 404 를 반환한다.")
    void orderNotExistMenu() {
        final var response = 주문_결제_요청(멤버2_ID, "notExistMenuUUID");

        Assertions.assertThat(response.statusCode()).isEqualTo(404);
    }
}
