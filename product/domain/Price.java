package com.yeol.market.product.domain;

import com.yeol.market.common.exception.InvalidPriceException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Price {

    @Column(name = "price")
    private Long value;

    public Price(final Long value) {
        validateNotNull(value);
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(final Long value) {
        if (value < 0) {
            throw new InvalidPriceException("금액은 음수일 수 없습니다.");
        }
    }

    private void validateNotNull(final Long value) {
        if (value == null) {
            throw new InvalidPriceException("금액은 null일 수 없습니다.");
        }
    }
}
