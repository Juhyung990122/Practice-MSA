package com.yeol.market.domain.repository;

import com.yeol.market.domain.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.uuid = :menuUuid")
    Optional<Product> findByUuId(String menuUuid);
}
