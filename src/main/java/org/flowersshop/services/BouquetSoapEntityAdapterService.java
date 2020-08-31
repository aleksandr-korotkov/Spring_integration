package org.flowersshop.services;


import org.flowersshop.bindings.Bouquet;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BouquetSoapEntityAdapterService {

    private BouquetService bouquetService;

    @Autowired
    public void setBouquetRepository(BouquetService bouquetService) {
        this.bouquetService = bouquetService;
    }

    List<Bouquet> findByName(String name) throws EmptyResultSetException {
        return entityAdapter(bouquetService.findByName(name));
    }

    List<Bouquet> findByPrice(BigDecimal price) throws EmptyResultSetException {
        return entityAdapter(bouquetService.findByPrice(price));
    }

    List<Bouquet> findByNameAndPrice(String name, BigDecimal price) throws EmptyResultSetException {
        return entityAdapter(bouquetService.findByNameAndPrice(name, price));
    }

    List<Bouquet> findAll(){
        return entityAdapter(bouquetService.findAll());
    }

    org.flowersshop.entities.Bouquet createBouquet(String name, BigDecimal price){
        org.flowersshop.entities.Bouquet bouquet = new org.flowersshop.entities.Bouquet();
        bouquet.setName(name);
        bouquet.setPrice(price);
        return bouquetService.saveBouquet(bouquet);
    }

    void deleteBouquet(Long id){
         bouquetService.deleteBouquet(id);
    }

    private List<Bouquet> entityAdapter(List<org.flowersshop.entities.Bouquet> bouquets){
        return bouquets.stream().map(bouquet -> {
            Bouquet b = new Bouquet();
            b.setId(bouquet.getId());
            b.setName(bouquet.getName());
            b.setPrice(bouquet.getPrice());
            return b;
        }).collect(Collectors.toList());
    }


}
