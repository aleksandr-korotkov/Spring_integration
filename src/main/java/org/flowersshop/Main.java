package org.flowersshop;

import org.flowersshop.entities.Sale;
import org.flowersshop.repositories.SaleRepository;
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
        SaleRepository bean = run.getBean(SaleRepository.class);
        Iterable<Sale> allById = bean.findAllById(Collections.singleton(1l));
        System.out.println(allById);

    }
}