package com.yeol.market.product.application;

import static com.yeol.market.product.fixture.ProductFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

i
import com.yeol.market.product.utils.ServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductServiceTest extends ServiceTest {

    @BeforeEach
    void setup() {
        상품_등록(당근);
        상품_등록(감자);
        상품_등록(대파);
    }

    @Test
    @DisplayName("상품 전체를 불러온다.")
    void getAllProducts() {
        final var actual = productService.getAllProducts();

        assertAll(
                () -> assertThat(actual).hasSize(3),
                () -> assertThat(actual)
                        .extracting("name")
                        .contains("당근", "감자", "대파")
        );
    }

    @Test
    @DisplayName("단일 상품를 불러웁니다.")
    void getProduct() {
        final Prduct 메리딸기 = 상품_등록(new Product("고구마", 5000L));
        final var actual = productService.getProduct(메리딸기.getUuid());

        assertThat(actual.getName()).isEqualTo("고구마");
    }

    @Test
    @DisplayName("단일 상품를 불러웁니다.")
    void getProduct_NotFoundProduct() {
        assertThatThrownBy(() -> productService.getProduct("NotFoundProductUUID"))
                .isInstanceOf(NotFoundProductExcepton.class)
                .hasMessage("해당 상품을 찾을 수 없습니다.");
    }
}
