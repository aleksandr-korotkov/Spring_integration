package org.flowersshop.repositories;

import org.flowersshop.entities.Flower;
import org.springframework.data.repository.CrudRepository;

public interface FlowersRepository extends CrudRepository<Flower, Long> {

}
