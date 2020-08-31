package org.flowersshop.repositories;

import org.flowersshop.entities.Bouquet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BouquetRepository extends CrudRepository<Bouquet,Long> {

    List<Bouquet> findAll();

    Optional<List<Bouquet>> findByName(String name);

    Optional<Bouquet> findById(Long id);

    Optional<List<Bouquet>> findByPrice(BigDecimal price);

    Optional<List<Bouquet>> findByNameAndPrice(String name, BigDecimal price);

    Bouquet save(Bouquet bouquet);

    void deleteById(Long id);

}
