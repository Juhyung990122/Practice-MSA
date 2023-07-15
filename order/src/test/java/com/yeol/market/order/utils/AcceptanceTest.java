package com.yeol.market.order.utils;

import com.yeol.market.OrderApplication;
import com.yeol.market.order.config.DatabaseCleaner;
import com.yeol.market.order.fixture.ProductFixture;
import com.yeol.market.order.domain.Order;
import com.yeol.market.order.domain.repository.OrderRepository;
import com.yeol.market.order.support.FakeStatisticPlatformController;
import com.yeol.market.order.ui.dto.OrderRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = OrderApplication.class)
public abstract class AcceptanceTest {

    @LocalServerPort
    int port;
    @Autowired
    protected OrderRepository orderRepository;
    @Autowired
    protected PlatformTransactionManager transactionManager;
    protected TransactionTemplate transactionTemplate;
    @MockBean
    protected FakeStatisticPlatformController fakeStatisticPlatformController;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    protected String 당근_UUID;
    protected String 대파_UUID;
    protected String 감자_UUID;
    protected String 양배추_UUID;
    protected String 양파_UUID;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        당근_UUID = ProductFixture.당근.getUuid();
        대파_UUID = ProductFixture.대파.getUuid();
        감자_UUID = ProductFixture.감자.getUuid();
        양배추_UUID = ProductFixture.양배추.getUuid();
        양파_UUID = ProductFixture.양파.getUuid();
    }

    protected Order 주문내역_등록(final Order order) {
        return orderRepository.save(order);
    }

    protected ExtractableResponse<Response> 주문_결제_요청(final Long memberId, final String menuUuid) {
        final var orderRequest = new OrderRequest(memberId, menuUuid);
        return post("/orders", orderRequest);
    }

    protected void 다중_주문_결제_요청(final Long memberId, final String menuUuid, final Integer count) {
        for (int i = 0; i < count; i++) {
            주문_결제_요청(memberId, menuUuid);
        }
    }


    protected ExtractableResponse<Response> post(final String uri, final Object body) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when().post(uri)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> get(final String uri) {
        return RestAssured.given().log().all()
                .when().get(uri)
                .then().log().all()
                .extract();
    }

    @AfterEach
    void tearDown() {
        databaseCleaner.clear();
    }

}
