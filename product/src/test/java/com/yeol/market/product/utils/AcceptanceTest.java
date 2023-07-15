package com.yeol.market.product.utils;

import com.yeol.market.config.DatabaseCleaner;
import com.yeol.market.config.DatabaseCleanerConfig;
import com.yeol.market.domain.Point;
import com.yeol.market.domain.Product;
import com.yeol.market.domain.repository.PointRepository;
import com.yeol.market.domain.repository.ProductRepository;
import com.yeol.market.fixture.PointFixture;
import com.yeol.market.fixture.ProductFixture;
import com.yeol.market.order.domain.Order;
import com.yeol.market.order.domain.repository.OrderRepository;
import com.yeol.market.order.support.FakeStatisticPlatformController;
import com.yeol.market.order.ui.dto.OrderRequest;
import com.yeol.market.ui.dto.PointChargeRequest;
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
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

@Import(value = {DatabaseCleanerConfig.class})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class AcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected PointRepository pointRepository;
    @Autowired
    protected OrderRepository orderRepository;
    @Autowired
    protected DatabaseCleaner databaseCleaner;
    @Autowired
    protected PlatformTransactionManager transactionManager;
    protected TransactionTemplate transactionTemplate;

    @MockBean
    protected FakeStatisticPlatformController fakeStatisticPlatformController;

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

        당근_UUID = 상품_등록(ProductFixture.당근).getUuid();
        대파_UUID = 상품_등록(ProductFixture.대파).getUuid();
        감자_UUID = 상품_등록(ProductFixture.감자).getUuid();
        양배추_UUID = 상품_등록(ProductFixture.양배추).getUuid();
        양파_UUID = 상품_등록(ProductFixture.양파).getUuid();

        포인트_등록(new Point(PointFixture.멤버1_ID, 100000L));
        포인트_등록(new Point(PointFixture.멤버2_ID, 10000L));
        포인트_등록(new Point(PointFixture.멤버3_ID, 0L));
    }

    protected Product 상품_등록(final Product product) {
        return productRepository.save(product);
    }

    protected Point 포인트_등록(final Point point) {
        return pointRepository.save(point);
    }

    protected Order 주문내역_등록(final Order order) {
        return orderRepository.save(order);
    }

    protected ExtractableResponse<Response> 상품_목록_조회_요청() {
        return get("/products");
    }

    protected ExtractableResponse<Response> 포인트_충전_요청(final Long memberId, final Long balance) {
        final var chargeRequest = new PointChargeRequest(memberId, balance);
        return post("/points/charge", chargeRequest);
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

    protected ExtractableResponse<Response> 상위_3개_상품_조회_요청() {
        return get("/products/ranking");
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

    public Long 포인트_잔액_확인(final Long memberId) {
        final Long balance = transactionTemplate.execute(status -> {
            final Point point = pointRepository.findByMemberId(memberId).get();
            return point.getBalance();
        });
        return balance;
    }

    @AfterEach
    void tearDown() {
        databaseCleaner.execute();
    }

}
