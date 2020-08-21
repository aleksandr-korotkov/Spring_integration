package org.flowersshop.repositories;

import java.util.List;
import java.util.Optional;

import org.flowersshop.entities.Customer;

public interface CustomerRepository {

    Optional<List<Customer>> findAll();

    Optional<Customer> findById(long id);

    Optional<Customer> findByFirstName(String firstname);

    Optional<Customer> findByLastName(String lastname);

    Optional<Customer> findByPhone(String phone);

    void createCustomer(Customer customer);

    boolean deleteCustomer(Long id);

    Optional<Customer> updateCustomer(Long id, Customer customer);

    Optional<Customer> findByUsername(String username);
}