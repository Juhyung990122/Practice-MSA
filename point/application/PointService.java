package com.yeol.market.point.application;

import com.yeol.market.common.exception.NotFoundPointException;
import com.yeol.market.order.application.event.PayEvent;
import com.yeol.market.point.application.dto.PointChargeDto;
import com.yeol.market.point.application.dto.PointResponse;
import com.yeol.market.point.domain.Point;
import com.yeol.market.point.domain.repository.PointRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Transactional
public class PointService {

    private final PointRepository pointRepository;

    public PointService(final PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public PointResponse chargePoint(final PointChargeDto pointChargeDto) {
        final Point point = findPointByMemberId(pointChargeDto.getMemberId());

        point.charge(pointChargeDto.getChargePrice());

        return PointResponse.of(point);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void spendPoint(final PayEvent payEvent) {
        final Point point = findPointByMemberId(payEvent.getMemberId());
        point.spend(payEvent.getPaymentPrice());
    }

    private Point findPointByMemberId(final Long memberId) {
        return pointRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundPointException("해당 회원의 포인트를 찾을 수 없습니다."));
    }
}
