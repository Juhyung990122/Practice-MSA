package com.yeol.market.application.dto;


import com.yeol.market.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponse {

    private String uuid;
    private String name;
    private Long price;

    public static ProductResponse of(final Product product) {
        return new ProductResponse(product.getUuid(), product.getName(), product.getPrice());
    }
}
