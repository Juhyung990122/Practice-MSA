package com.yeol.market.point.domain;

import com.yeol.market.common.exception.InvalidPriceException;
import com.yeol.market.common.exception.NotEnoughBalanceException;
import com.yeol.market.product.domain.Price;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "point")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private Long balance;

    public Point(Long memberId) {
        this.memberId = memberId;
        this.balance = 0L;
    }

    public Point(final Long memberId, final Long balance) {
        this.memberId = memberId;
        this.balance = new Price(balance).getValue();
    }

    public void charge(final Long chargePrice) {
        if (chargePrice <= 0) {
            throw new InvalidPriceException("충전금액은 음수일 수 없습니다.");
        }
        balance += chargePrice;
    }

    public void spend(final Long paymentPrice) {
        final Long paidBalance = balance - paymentPrice;
        if (paidBalance < 0) {
            throw new NotEnoughBalanceException("잔액이 부족합니다.");
        }
        balance = paidBalance;
    }
}
