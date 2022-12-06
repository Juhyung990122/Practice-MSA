package com.yeol.market.order.application;


import static com.yeol.market.fixture.PointFixture.멤버1_ID;
import static com.yeol.market.fixture.PointFixture.포인트_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.yeol.market.order.application.dto.OrderResponse;
import com.yeol.market.order.domain.OrderHistory;
import com.yeol.market.order.support.FakeStatisticPlatformController;
import com.yeol.market.product.application.dto.ProductResponse;
import com.yeol.market.product.domain.Product;
import com.yeol.market.utils.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class OrderServiceTest extends ServiceTest {

    @Autowired
    private PlatformTransactionManager tm;
    private TransactionTemplate transaction;
    @MockBean
    private FakeStatisticPlatformController fakeStatisticPlatformController;

    @Test
    @DisplayName("주문을 한다.")
    void order() {
        transaction = new TransactionTemplate(tm);
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transaction.execute((status) -> {
            포인트_등록(포인트_생성(멤버1_ID, 10000L));
            final var menu = 상품_등록(new Product("후추", 1000L));
            return menu;
        });

        final String 후추_ID = transaction.execute((status) -> {
            final OrderResponse order = orderService.order(멤버1_ID, ProductResponse.of(new Product("코코아", 1000L)));
            return order.getMenuUuid();
        });

        verify(fakeStatisticPlatformController, atLeastOnce()).catchOrderHistory(any(OrderHistory.class));
        assertThat(orderRepository.findById(1L).get().getProductUuid()).isEqualTo(후추_ID);
        assertThat(pointRepository.findByMemberId(멤버1_ID).get().getBalance()).isEqualTo(9000L);
    }

}
