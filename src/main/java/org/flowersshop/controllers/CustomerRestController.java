package org.flowersshop.controllers;

import javax.validation.Valid;
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
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers() throws EmptyResultSetException {
        return ResponseEntity.ok().body(customerRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomerByName(@PathVariable("id") Long id) throws EmptyResultSetException {
        return ResponseEntity.ok().body(customerRepository.findById(id).orElseThrow(EmptyResultSetException::new));
    }

    @PostMapping
    @ResponseBody
    public void save(@RequestBody @Valid Customer customer) {
        customerRepository.save(customer);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) throws EmptyResultSetException {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.deleteById(id);
        }
        return ResponseEntity.ok().body(customer.orElseThrow(EmptyResultSetException::new));
    }
}