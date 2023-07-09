package com.yeol.market.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@TestConfiguration
@RequiredArgsConstructor
public class TransactionManagerConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
