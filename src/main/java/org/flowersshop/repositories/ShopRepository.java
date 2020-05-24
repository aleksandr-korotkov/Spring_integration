package org.flowersshop.repositories;

import org.flowersshop.entities.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepository extends CrudRepository<Shop, Long> {
}
