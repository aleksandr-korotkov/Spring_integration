package org.flowersshop.services;

import org.flowersshop.entities.Sale;
import org.flowersshop.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalesService {

    private SaleRepository saleRepository;

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Iterable<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Iterable<Sale> findByDate(LocalDate date) {
        return saleRepository.findByDate(date);
    }
}