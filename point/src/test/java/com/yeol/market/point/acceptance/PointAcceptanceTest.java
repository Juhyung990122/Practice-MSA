package com.yeol.market.point.acceptance;


import static com.yeol.market.fixture.PointFixture.멤버3_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.yeol.market.utils.AcceptanceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointAcceptanceTest extends AcceptanceTest {
    @Test
    @DisplayName("사용자의 포인트를 충전한다.")
    void chargePoint() {
        final var response = 포인트_충전_요청(PointFixture.멤버3_ID, 5000L);

        assertAll(
                () -> Assertions.assertThat(response.statusCode()).isEqualTo(200),
                () -> Assertions.assertThat(포인트_잔액_확인(PointFixture.멤버3_ID)).isEqualTo(5000L)
        );
    }

    @Test
    @DisplayName("존재하지 않는 사용자의 포인트를 충전한다.")
    void chargePointNotExistMember() {
        final var response = 포인트_충전_요청(9999L, 15000L);

        Assertions.assertThat(response.statusCode()).isEqualTo(404);
    }
}
