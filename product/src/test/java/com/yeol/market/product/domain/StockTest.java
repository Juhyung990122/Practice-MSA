package com.yeol.market.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.yeol.market.common.exception.InvalidStockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockTest {

    @Test
    @DisplayName("stock를 생성한다.")
    void stock() {
        final var actual = new Stock(100L);

        assertThat(actual.getValue()).isEqualTo(100);
    }

    @Test
    @DisplayName("stock를 null로 생성하려는 경우 예외를 반환한다.")
    void stock_Nullstock() {
        assertThatThrownBy(() -> new Stock(null))
                .isInstanceOf(InvalidStockException.class)
                .hasMessage("수량은 null일 수 없습니다.");
    }

    @Test
    @DisplayName("stock를 음수로 생성하려는 경우 예외를 반환한다.")
    void stock_Negativestock() {
        assertThatThrownBy(() -> new Stock(-1L))
                .isInstanceOf(InvalidStockException.class)
                .hasMessage("수량은 음수일 수 없습니다.");
    }
}
