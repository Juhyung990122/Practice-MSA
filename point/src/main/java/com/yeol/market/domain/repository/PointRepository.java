package com.yeol.market.domain.repository;

import com.yeol.market.point.domain.Point;
import java.util.Optional;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointRepository extends JpaRepository<Point, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Point p where p.memberId = :memberId")
    Optional<Point> findByMemberId(@Param("memberId") final Long memberId);
}
