package com.yeol.market.point.ui;

import com.yeol.market.point.application.PointService;
import com.yeol.market.point.application.dto.PointResponse;
import com.yeol.market.point.ui.dto.PointChargeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/points")
public class PointController {

    private final PointService pointService;

    public PointController(final PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping
    @RequestMapping("/charge")
    public ResponseEntity<PointResponse> chargePoint(@RequestBody final PointChargeRequest pointChargeRequest) {
        final PointResponse pointResponse = pointService.chargePoint(pointChargeRequest.toServiceDto());
        return ResponseEntity.ok(pointResponse);
    }
}
