package org.flowersshop.services;

import org.flowersshop.entities.Customer;
import org.flowersshop.entities.Role;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.repositories.CustomerRepository;
import org.flowersshop.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return customerRepository.findByUsername(username).orElse(new Customer());
    }

    public Customer findUserById(Long userId) {
        Optional<Customer> userFromDb = customerRepository.findById(userId);
        return userFromDb.orElse(new Customer());
    }

    public List<Customer> allUsers() throws EmptyResultSetException {
        return customerRepository.findAll().orElseThrow(EmptyResultSetException::new);
    }

    public boolean saveUser(Customer customer) {
        if(customerRepository.findByUsername(customer.getUsername()).isPresent()){
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


    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
