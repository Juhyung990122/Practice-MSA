package com.yeol.market.order.ui;

import static com.yeol.market.fixture.PointFixture.멤버1_ID;
import static com.yeol.market.fixture.ProductFixture.당근;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yeol.market.order.application.dto.OrderResponse;
import com.yeol.market.order.ui.dto.OrderRequest;
import com.yeol.market.product.application.dto.ProductResponse;
import com.yeol.market.restdocs.RestDocsTestSupporter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class OrderControllerTest extends RestDocsTestSupporter {

    @Test
    @DisplayName("당근을 주문한다.")
    void orderCoffee() throws Exception {
        final var orderRequest = new OrderRequest(멤버1_ID, "당근의 uuid");
        given(productService.getProduct(any()))
                .willReturn(ProductResponse.of(당근));
        given(orderService.order(any(), any(ProductResponse.class)))
                .willReturn(new OrderResponse(당근.getName(), 당근.getPrice()));

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)
                ))
                .andExpect(status().isOk())
                .andDo(restDocs.document());
    }
}
