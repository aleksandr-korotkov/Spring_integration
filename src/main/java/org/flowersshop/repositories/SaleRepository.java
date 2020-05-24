package org.flowersshop.repositories;

import org.flowersshop.entities.Sale;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale,Long> {
}
