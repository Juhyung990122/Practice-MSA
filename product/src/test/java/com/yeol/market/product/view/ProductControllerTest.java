package com.yeol.market.product.view;

import static com.yeol.market.fixture.ProductFixture.당근;
import static com.yeol.market.fixture.ProductFixture.대파;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yeol.market.product.application.dto.ProductResponse;
import com.yeol.market.restdocs.RestDocsTestSupporter;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductControllerTest extends RestDocsTestSupporter {

    @Test
    @DisplayName("상품 전체를 불러온다.")
    void getAllMenu() throws Exception {

        given(productService.getAllProducts())
                .willReturn(List.of(ProductResponse.of(당근), ProductResponse.of(대파)));

        mockMvc.perform(
                get("/products")
        )
                .andExpect(status().isOk())
                .andDo(restDocs.document());
    }
}
