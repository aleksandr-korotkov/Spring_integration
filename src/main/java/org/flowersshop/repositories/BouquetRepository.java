package org.flowersshop.repositories;

import org.flowersshop.entities.Bouquet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BouquetRepository {

    Optional<List<Bouquet>> findByName(String name);

    Optional<List<Bouquet>> findByPrice(BigDecimal price);

    Optional<List<Bouquet>> findByNameAndPrice(String name, BigDecimal price);

    Optional<List<Bouquet>> findAll();

    Long createBouquet(String name, BigDecimal price);

    boolean updateBouquet(Long id, String name, BigDecimal price);

    boolean deleteBouquet(Long id);
}
