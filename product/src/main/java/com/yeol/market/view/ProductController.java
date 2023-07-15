package com.yeol.market.view;

import com.yeol.market.application.ProductService;
import com.yeol.market.application.dto.ProductResponse;
import com.yeol.market.domain.service.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final RankingService rankingService;

    public ProductController(final ProductService productService,
                             final RankingService rankingService) {
        this.productService = productService;
        this.rankingService = rankingService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(value = "/ranking")
    public ResponseEntity<List<ProductResponse>> getTop3Products(){
        return ResponseEntity.ok(rankingService.findTop3Menu());
    }

    @GetMapping(value = "/{menuUuid}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String menuUuid){
        return ResponseEntity.ok(productService.getProduct(menuUuid));
    }
}
