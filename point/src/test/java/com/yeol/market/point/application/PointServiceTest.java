package com.yeol.market.point.application;

import static com.yeol.market.fixture.PointFixture.멤버1_ID;
import static com.yeol.market.fixture.PointFixture.멤버2_ID;
import static com.yeol.market.fixture.PointFixture.멤버3_ID;
import static com.yeol.market.fixture.PointFixture.포인트_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.yeol.market.common.exception.NotFoundPointException;
import com.yeol.market.order.application.event.PayEvent;
import com.yeol.market.point.application.dto.PointChargeDto;
import com.yeol.market.point.domain.Point;
import com.yeol.market.utils.ServiceTest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

class PointServiceTest extends ServiceTest {

    @BeforeEach
    void setup() {
        포인트_등록(포인트_생성(멤버1_ID, 10000L));
        포인트_등록(포인트_생성(멤버2_ID, 20000L));
        포인트_등록(포인트_생성(멤버3_ID, 30000L));
    }

    @Test
    @DisplayName("요청된 member의 포인트를 충전한다.")
    void chargePoint() {
        final var pointChargeDto = new PointChargeDto(1L, 5000L);

        final var pointResponse = pointService.chargePoint(pointChargeDto);

        assertAll(
                () -> assertThat(pointResponse.getMemberId()).isEqualTo(멤버1_ID),
                () -> assertThat(pointResponse.getBalance()).isEqualTo(15000L)
        );
    }

    @Test
    @DisplayName("member의 포인트가 존재하지 않는 경우 예외를 발생시킨다.")
    void chargePoint_NotFoundPoint() {
        final var pointChargeDto = new PointChargeDto(9999L, 5000L);

        assertThatThrownBy(() -> pointService.chargePoint(pointChargeDto))
                .isInstanceOf(NotFoundPointException.class)
                .hasMessage("해당 회원의 포인트를 찾을 수 없습니다.");
    }

    @Test
    void spendPoint() {
        final var pointChargeDto = new PayEvent(멤버1_ID, 5000L);

        pointService.spendPoint(pointChargeDto);

        final Point point = pointRepository.findByMemberId(멤버1_ID).get();
        assertThat(point.getBalance()).isEqualTo(5000L);
    }

    @Nested
    class concurrency {

        private static final long MEMBER_ID = 4L;

        @Autowired
        private PlatformTransactionManager tm;
        private TransactionTemplate transaction;

        @BeforeEach
        void setup() {
            transaction = new TransactionTemplate(tm);
            transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transaction.execute((status -> pointRepository.saveAndFlush(포인트_생성(MEMBER_ID, 10000L))));
        }

        @Test
        @DisplayName("동시에 포인트 충전을 하면 순차적으로 처리된다.")
        void chargePoint_Concurrency() throws InterruptedException {
            AtomicInteger successCount = new AtomicInteger();
            final Integer numberOfExecute = 3;
            final ExecutorService service = Executors.newFixedThreadPool(3);
            final CountDownLatch latch = new CountDownLatch(numberOfExecute);
            final var pointChargeDto = new PointChargeDto(MEMBER_ID, 5000L);

            for (int i = 0; i < numberOfExecute; i++) {
                service.submit(() -> {
                    transaction.execute((status -> {
                        pointService.chargePoint(pointChargeDto);
                        sleep();
                        successCount.getAndIncrement();
                        latch.countDown();
                        return null;
                    }));
                });
            }
            latch.await();

            final var resultPoint = pointRepository.findByMemberId(MEMBER_ID).get();
            assertThat(successCount.get()).isEqualTo(3);
            assertThat(resultPoint.getBalance()).isEqualTo(25000L);
        }

        @Test
        @DisplayName("동시에 포인트를 차감하면 순차적으로 처리된다.")
        void spendPoint_Concurrency() throws InterruptedException {
            AtomicInteger successCount = new AtomicInteger();
            final Integer numberOfExecute = 3;
            final ExecutorService service = Executors.newFixedThreadPool(3);
            final CountDownLatch latch = new CountDownLatch(numberOfExecute);
            final var payEvent = new PayEvent(MEMBER_ID, 3000L);

            for (int i = 0; i < numberOfExecute; i++) {
                service.submit(() -> {
                    transaction.execute((status -> {
                        pointService.spendPoint(payEvent);
                        sleep();
                        successCount.getAndIncrement();
                        latch.countDown();
                        return null;
                    }));
                });
            }
            latch.await();

            final var resultPoint = pointRepository.findByMemberId(MEMBER_ID).get();
            assertThat(successCount.get()).isEqualTo(3);
            assertThat(resultPoint.getBalance()).isEqualTo(1000L);
        }

        private void sleep() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
