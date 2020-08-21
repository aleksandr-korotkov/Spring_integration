package org.flowersshop.services;

import org.flowersshop.entities.Bouquet;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.repositories.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BouquetService {

    private BouquetRepository bouquetRepository;

    @Autowired
    public void setBouquetRepository(BouquetRepository bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }

    public List<Bouquet> findAll() throws EmptyResultSetException {
        return bouquetRepository.findAll().orElseThrow(EmptyResultSetException::new);
    }

    public List<Bouquet> findByName(String name) throws EmptyResultSetException {
        return bouquetRepository.findByName(name).orElseThrow(EmptyResultSetException::new);
    }

    public List<Bouquet> findByPrice(BigDecimal price) throws EmptyResultSetException {
        return bouquetRepository.findByPrice(price).orElseThrow(EmptyResultSetException::new);
    }

    public List<Bouquet> findByNameAndPrice(String name, BigDecimal price) throws EmptyResultSetException {
        return bouquetRepository.findByNameAndPrice(name, price).orElseThrow(EmptyResultSetException::new);
    }

    public List<Bouquet> findByMinPrice(BigDecimal minPrice) throws EmptyResultSetException {
        return bouquetRepository.findByMinPrice(minPrice).orElseThrow(EmptyResultSetException::new);
    }

    public Bouquet findById(Long id) throws EmptyResultSetException {
        return bouquetRepository.findById(id).orElseThrow(EmptyResultSetException::new);
    }

    public boolean deleteBouquet(Long id) {
        return bouquetRepository.deleteBouquet(id);
    }

    public Long createBouquet(Bouquet bouquet) {
        return bouquetRepository.createBouquet(bouquet.getName(), bouquet.getPrice());
    }

    public Boolean updateBouquet(Long id, Bouquet bouquet) {
        return bouquetRepository.updateBouquet(id, bouquet.getName(), bouquet.getPrice());
    }
}