package org.flowersshop.controllers;

import org.flowersshop.entities.Customer;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/customers")
public class CustomerRestController {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() throws EmptyResultSetException {
        return ResponseEntity.ok().body(customerRepository.findAll().orElseThrow(EmptyResultSetException::new));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable("id") Long id) throws EmptyResultSetException {
        return ResponseEntity.ok().body(customerRepository.findById(id).orElseThrow(EmptyResultSetException::new));
    }

    @PostMapping
    public void createCustomer(@RequestBody Customer customer) {
        customerRepository.createCustomer(customer);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer( @PathVariable("id") Long id, @RequestBody Customer customer) throws EmptyResultSetException {
        return ResponseEntity.ok().body(customerRepository.updateCustomer(id, customer).
                orElseThrow(EmptyResultSetException::new));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) throws EmptyResultSetException {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.deleteCustomer(id);
        }
        return ResponseEntity.ok().body(customer.orElseThrow(EmptyResultSetException::new));
    }
}