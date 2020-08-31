package org.flowersshop.services;

import org.flowersshop.entities.Sale;
import org.flowersshop.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class SalesService {

    private SaleRepository saleRepository;
    Pageable firstPageWithTwoElements = PageRequest.of(0,2);

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Iterable<Sale> findAll(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    public List<Sale> findAllBySum(BigDecimal sum){
        return saleRepository.findAllByTotalSum(sum,firstPageWithTwoElements);
    }

    public List<Sale> findAll(){
        return saleRepository.findAll();
    }
}