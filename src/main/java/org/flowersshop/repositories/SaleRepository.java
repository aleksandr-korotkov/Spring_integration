package org.flowersshop.repositories;

import org.flowersshop.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SaleRepository extends PagingAndSortingRepository<Sale, Long>, CrudRepository<Sale,Long> {

    List<Sale> findAllByTotalSum(BigDecimal price, Pageable pageable);

    Page<Sale> findAll(Pageable pageable);

    List<Sale> findAll();
}
