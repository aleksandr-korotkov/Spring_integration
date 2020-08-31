package org.flowersshop.services;

import org.flowersshop.entities.Bouquet;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.repositories.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@CacheConfig(cacheNames = "bouquets")
public class BouquetService {

    private BouquetRepository bouquetRepository;

    @Autowired
    public void setBouquetRepository(BouquetRepository bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }

    public List<Bouquet> findAll() {
        return bouquetRepository.findAll();
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

    public Bouquet findById(Long id) throws EmptyResultSetException {
        return bouquetRepository.findById(id).orElseThrow(EmptyResultSetException::new);
    }

    public void deleteBouquet(Long id) {
        bouquetRepository.deleteById(id);
    }

    public Bouquet saveBouquet(Bouquet bouquet) {
        return bouquetRepository.save(bouquet);
    }
}