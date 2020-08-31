package org.flowersshop;

import org.flowersshop.entities.*;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.mybatis.AddressMapper;
import org.flowersshop.mybatis.BouquetMapper;
import org.flowersshop.mybatis.SalesMapper;
import org.flowersshop.mybatis.ShopMapper;
import org.flowersshop.repositories.AddressRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Set;


@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class Main{
    public static void main(String[] args) throws EmptyResultSetException {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
        SalesMapper mapper = run.getBean(SalesMapper.class);

        //System.out.println(mapper.findById(1l));
        List<Sale> sales = mapper.findAll();
        for(Sale f : sales){
            System.out.println(f);
        }

    }
}