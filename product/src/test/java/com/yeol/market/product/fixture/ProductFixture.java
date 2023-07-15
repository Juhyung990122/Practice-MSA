package com.yeol.market.product.fixture;


import com.yeol.market.domain.Product;

public class ProductFixture {

    public static Product 당근 = new Product("1111","당근", 3_000L);
    public static Product 대파 = new Product("1112","대파", 3_500L);
    public static Product 양배추 = new Product("1113","양배추", 4_000L);
    public static Product 양파 = new Product("1114","양파", 4_500L);
    public static Product 감자 = new Product("1115","감자", 5_000L);

    public static Product 메뉴_생성(final String uuid, final String name, final Long price) {
        return new Product(uuid, name, price);
    }
}
