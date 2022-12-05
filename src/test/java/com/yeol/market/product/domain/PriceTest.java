package com.yeol.market.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.yeol.market.common.exception.InvalidPriceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceTest {

    @Test
    @DisplayName("price를 생성한다.")
    void Price() {
        final var actual = new Price(10000L);

        assertThat(actual.getValue()).isEqualTo(10000);
    }

    @Test
    @DisplayName("price를 null로 생성하려는 경우 예외를 반환한다.")
    void Price_NullPrice() {
        assertThatThrownBy(() -> new Price(null))
                .isInstanceOf(InvalidPriceException.class)
                .hasMessage("금액은 null일 수 없습니다.");
    }

    @Test
    @DisplayName("price를 음수로 생성하려는 경우 예외를 반환한다.")
    void Price_NegativePrice() {
        assertThatThrownBy(() -> new Price(-1L))
                .isInstanceOf(InvalidPriceException.class)
                .hasMessage("금액은 음수일 수 없습니다.");
    }
}
