package com.yeol.market.product.domain.repository;

import com.yeol.market.product.domain.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.uuid = :menuUuid")
    Optional<Product> findByUuId(String menuUuid);
}
