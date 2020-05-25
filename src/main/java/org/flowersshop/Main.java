package org.flowersshop;

import org.flowersshop.entities.Bouquet;
import org.flowersshop.entities.Sale;
import org.flowersshop.repositories.SaleRepository;
import org.flowersshop.services.SalesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collections;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class Main{
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
    }
}