package com.yeol.market.order.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@TestConfiguration
public class TransactionManagerConfig {
    @Bean
    public PlatformTransactionManager platformTransactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
