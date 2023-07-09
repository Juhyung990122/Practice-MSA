package com.yeol.market.point.ui;

import static com.yeol.market.fixture.PointFixture.멤버1_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yeol.market.point.application.dto.PointChargeDto;
import com.yeol.market.point.application.dto.PointResponse;
import com.yeol.market.point.ui.dto.PointChargeRequest;
import com.yeol.market.restdocs.RestDocsTestSupporter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class PointControllerTest extends RestDocsTestSupporter {

    @Test
    @DisplayName("입력받은 멤버의 포인트를 충전한다.")
    void pointCharge() throws Exception {

        final var pointChargeRequest = new PointChargeRequest(멤버1_ID, 1000L);
        final var pointChargeResponse = new PointResponse(멤버1_ID, 11000L);

        given(pointService.chargePoint(any(PointChargeDto.class))).willReturn(pointChargeResponse);

        mockMvc.perform(
                post("/points/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(pointChargeRequest))
        )
                .andExpect(status().isOk())
                .andDo(restDocs);
    }
}
