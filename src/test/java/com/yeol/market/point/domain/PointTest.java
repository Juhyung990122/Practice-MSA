package com.yeol.market.point.domain;

import static com.yeol.market.fixture.PointFixture.멤버1_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.yeol.market.common.exception.InvalidPriceException;
import com.yeol.market.common.exception.NotEnoughBalanceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    @DisplayName("포인트를 충전한다.")
    void charge() {
        final Point point = new Point(멤버1_ID);

        point.charge(1000L);

        assertThat(point.getBalance()).isEqualTo(1000);
    }

    @Test
    @DisplayName("포인트를 충전할 금액이 음수라면 예외를 반환한다.")
    void charge_NegativeBalance() {
        final Point point = new Point(멤버1_ID);

        assertThatThrownBy(() -> point.charge(-1000L))
                .isInstanceOf(InvalidPriceException.class)
                .hasMessage("충전금액은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("포인트를 사용한다.")
    void spend() {
        final var point = new Point(멤버1_ID, 10000L);

        point.spend(1000L);

        assertThat(point.getBalance()).isEqualTo(9000L);
    }

    @Test
    @DisplayName("잔액 포인트보다 더 많은 금액을 사용하려 할 경우 예외를 반환한다.")
    void spend_NotEnoughBalance() {
        final var point = new Point(멤버1_ID, 10000L);

        assertThatThrownBy(() -> point.spend(20000L))
                .isInstanceOf(NotEnoughBalanceException.class)
                .hasMessage("잔액이 부족합니다.");
    }
}
