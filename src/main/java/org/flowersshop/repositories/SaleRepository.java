package org.flowersshop.repositories;

import org.flowersshop.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;

public interface SaleRepository extends PagingAndSortingRepository<Sale, Integer> {

    List<Sale> findAllByTotalSum(BigDecimal price, Pageable pageable);

    Page<Sale> findAll(Pageable pageable);
}
