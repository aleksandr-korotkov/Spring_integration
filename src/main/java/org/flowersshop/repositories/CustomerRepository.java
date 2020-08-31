package org.flowersshop.repositories;

import java.util.List;
import java.util.Optional;

import org.flowersshop.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer,Long> {

    List<Customer> findAll();

    Optional<Customer> findById(long id);

    Optional<Customer> findByFirstName(String firstname);

    Optional<Customer> findByLastName(String lastname);

    Optional<Customer> findByPhone(String phone);

    Customer save(Customer customer);

    void deleteById(Long id);

}