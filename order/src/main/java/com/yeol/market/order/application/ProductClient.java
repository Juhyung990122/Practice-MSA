package com.yeol.market.order.application;

import com.yeol.market.order.application.dto.ProductResponseDto;

public interface ProductClient {

    ProductResponseDto getProductByUUID(String menuUuId);
}
