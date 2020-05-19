package org.flowersshop.services;

import org.flowersshop.entities.Customer;
import org.flowersshop.entities.Role;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.repositories.CustomerRepository;
import org.flowersshop.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = null;
        try {
            customer = customerRepository.findByUsername(username).orElseThrow(EmptyResultSetException::new);
        } catch (EmptyResultSetException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer findUserById(Long userId) {
        Optional<Customer> userFromDb = customerRepository.findById(userId);
        return userFromDb.orElse(new Customer());
    }

    public List<Customer> allUsers() {
        return customerRepository.findAll().get();
    }

    public boolean saveUser(Customer customer) {
        Customer customerFromDB = customerRepository.findByUsername(customer.getUsername()).get();

        if (customerFromDB != null) {
            return false;
        }

        customer.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.createCustomer(customer);
        return true;
    }

    public boolean deleteUser(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteCustomer(id);
            return true;
        }
        return false;
    }

//    public List<Customer> usergtList(Long idMin) {
//        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
//                .setParameter("paramId", idMin).getResultList();
//    }
}
