package com.yeol.market.product.view;

import com.yeol.market.product.application.ProductService;
import com.yeol.market.product.application.dto.ProductResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllMenu() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
