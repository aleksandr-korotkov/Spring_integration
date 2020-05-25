package org.flowersshop.repositories;

import org.flowersshop.entities.Sale;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface SaleRepository extends CrudRepository<Sale,Long> {

    Iterable<Sale> findByDate(LocalDate date);
}
