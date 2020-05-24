package org.flowersshop.services;


import org.flowersshop.bindings.Bouquet;
import org.flowersshop.repositories.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BouquetSoapEntityAdapterService {

    private BouquetRepository bouquetRepository;

    @Autowired
    public void setBouquetRepository(BouquetRepository bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }

    List<Bouquet> findByName(String name){
        return entityAdapter(bouquetRepository.findByName(name).orElseThrow(EntityNotFoundException::new));
    }

    List<Bouquet> findByPrice(BigDecimal price){
        return entityAdapter(bouquetRepository.findByPrice(price).orElseThrow(EntityNotFoundException::new));
    }

    List<Bouquet> findByNameAndPrice(String name, BigDecimal price){
        return entityAdapter(bouquetRepository.findByNameAndPrice(name, price).orElseThrow(EntityNotFoundException::new));
    }

    List<Bouquet> findAll(){
        return entityAdapter(bouquetRepository.findAll().orElseThrow(EntityNotFoundException::new));
    }

    Long createBouquet(String name, BigDecimal price){
        return bouquetRepository.createBouquet(name,price);
    }

    boolean updateBouquet(Long id, String name, BigDecimal price){
        return bouquetRepository.updateBouquet(id,name,price);
    }

    boolean deleteBouquet(Long id){
        return bouquetRepository.deleteBouquet(id);
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
