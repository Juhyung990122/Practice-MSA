package com.yeol.market.point.domain.repository;

import static com.yeol.market.fixture.PointFixture.멤버1_ID;
import static com.yeol.market.fixture.PointFixture.멤버2_ID;
import static com.yeol.market.fixture.PointFixture.멤버3_ID;
import static com.yeol.market.fixture.PointFixture.포인트_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.yeol.market.common.config.QuerydslConfig;
import com.yeol.market.point.domain.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Import({QuerydslConfig.class})
class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    @BeforeEach
    void setup() {
        pointRepository.save(포인트_생성(멤버1_ID, 10000L));
        pointRepository.save(포인트_생성(멤버2_ID, 20000L));
        pointRepository.save(포인트_생성(멤버3_ID, 30000L));
    }

    @Test
    @DisplayName("memberId로 포인트 정보를 조회한다.")
    void findByMemberId() {
        final Point point = pointRepository.findByMemberId(멤버2_ID).get();

        assertAll(
                () -> assertThat(point.getMemberId()).isEqualTo(멤버2_ID),
                () -> assertThat(point.getBalance()).isEqualTo(20000L)
        );
    }
}
