package org.flowersshop.services;

import org.flowersshop.entities.Customer;
import org.flowersshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService implements UserDetailsService {

    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return customerRepository.findByFirstName(username).orElse(new Customer());
    }

    public Customer findUserById(Long userId) {
        Optional<Customer> customer = customerRepository.findById(userId);
        return customer.orElse(new Customer());
    }

    public List<Customer> allUsers() {
        return customerRepository.findAll();
    }

    public boolean saveUser(Customer customer) {
        if(customerRepository.findByFirstName(customer.getUsername()).isPresent()){
            return false;
        }
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        return true;
    }

    public boolean deleteUser(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
