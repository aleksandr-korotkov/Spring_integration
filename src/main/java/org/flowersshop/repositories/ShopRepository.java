package org.flowersshop.repositories;

import java.util.List;
import org.flowersshop.entities.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends CrudRepository<Shop,Long> {

    List<Shop> findByName (String name);


}
