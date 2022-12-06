package com.yeol.market.order.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String productUuid;

    private Long paymentPrice;

    private LocalDateTime createdTime;

    public Order(final String productUuid, final Long paymentPrice) {
        this.productUuid = productUuid;
        this.paymentPrice = paymentPrice;
        this.createdTime = LocalDateTime.now();
    }

    public Order(final Long memberId, final String productUuid, final Long paymentPrice) {
        this.memberId = memberId;
        this.productUuid = productUuid;
        this.paymentPrice = paymentPrice;
        this.createdTime = LocalDateTime.now();
    }
}
