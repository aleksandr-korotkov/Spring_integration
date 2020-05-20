package org.flowersshop.services;

import org.flowersshop.entities.Customer;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController("/customers")
@RequestMapping(value = "/customers")
public class CustomerRestService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerRestService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers() throws EmptyResultSetException {
        return ResponseEntity.ok().body(customerRepository.findAll().orElseThrow(EmptyResultSetException::new));
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomerByName(@PathVariable("id") Long id) throws EmptyResultSetException {
        return ResponseEntity.ok().body(customerRepository.findById(id).orElseThrow(EmptyResultSetException::new));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) throws EmptyResultSetException {
        return ResponseEntity.status(201).body(customerRepository.createCustomer(customer.getFirstName(),
                customer.getLastName(),customer.getPhone()).orElseThrow(EmptyResultSetException::new));
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Customer> updateCustomer( @PathVariable("id") Long id, @RequestBody @Valid Customer customer) throws EmptyResultSetException {
        return ResponseEntity.ok().body(customerRepository.updateCustomer(id,
                customer.getFirstName(), customer.getLastName(), customer.getPhone()).orElseThrow(EmptyResultSetException::new));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) throws EmptyResultSetException {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.deleteCustomer(id);
        }
        return ResponseEntity.ok().body(customer.orElseThrow(EmptyResultSetException::new));
    }
}