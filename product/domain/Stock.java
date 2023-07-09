package com.yeol.market.product.domain;

import com.yeol.market.common.exception.InvalidStockException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Stock {

    @Column(name = "stock")
    private Long value;

    public Stock(final Long value) {
        validateNull(value);
        validateNegative(value);
        this.value = value;
    }

    private void validateNull(final Long value){
        if(value == null){
            throw new InvalidStockException("수량은 null일 수 없습니다.");
        }
    }

    private void validateNegative(final Long value){
        if(value < 0){
            throw new InvalidStockException("수량은 음수일 수 없습니다.");
        }
    }
}
