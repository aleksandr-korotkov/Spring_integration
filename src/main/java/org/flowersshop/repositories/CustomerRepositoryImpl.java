package org.flowersshop.repositories;

import org.flowersshop.entities.Customer;
import org.flowersshop.repositories.mappers.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "customers")
public class CustomerRepositoryImpl implements CustomerRepository{

    private CustomerRowMapper customerRowMapper;
    private final JdbcTemplate jdbcTemplate;

    private final String QUERY_FOR_FIND_ALL = "select * from customers";
    private final String QUERY_FOR_FIND_BY_FIRSTNAME = "select * from customers where firstname = ?";
    private final String QUERY_FOR_FIND_BY_USERNAME = "select * from customers where username = ?";
    private final String QUERY_FOR_FIND_BY_LASTNAME = "select * from customers where lastname = ?";
    private final String QUERY_FOR_FIND_BY_PHONE = "select * from customers where phone = ?";
    private final String QUERY_FOR_FIND_BY_ID = "select * from customers where id = ?";
    private final String QUERY_FOR_CREATE = "insert into customers(firstname, lastname, phone, password,username) values(?,?,?,?,?)";
    private final String QUERY_FOR_DELETE = "delete from customers where id = ?";
    private final String QUERY_FOR_UPDATE = "update customers set firstname = ?, lastname = ?,phone = ?, password = ?, username = ? where id = ?";

    @Autowired
    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Cacheable
    @Override
    public Optional<List<Customer>> findAll(){
        return Optional.of(jdbcTemplate.query(QUERY_FOR_FIND_ALL, customerRowMapper));
    }

    @Cacheable
    @Override
    public Optional<Customer> findById(long id){
        return Optional.ofNullable(jdbcTemplate.queryForObject
                (QUERY_FOR_FIND_BY_ID, new Object[]{id}, customerRowMapper));
    }

    @Cacheable
    @Override
    public Optional<Customer> findByFirstName(String firstname){
        return Optional.ofNullable(jdbcTemplate.queryForObject
                (QUERY_FOR_FIND_BY_FIRSTNAME, new Object[]{firstname}, customerRowMapper));
    }

    @Cacheable
    @Override
    public Optional<Customer> findByLastName(String lastname){
        return Optional.ofNullable(jdbcTemplate.queryForObject
                (QUERY_FOR_FIND_BY_LASTNAME, new Object[]{lastname}, customerRowMapper));
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject
                    (QUERY_FOR_FIND_BY_USERNAME, new Object[]{username}, customerRowMapper));
        }
        catch (EmptyResultDataAccessException e){
            e.getMessage();
        }
        return Optional.empty();
    }

    @Cacheable
    @Override
    public Optional<Customer> findByPhone(String phone){
        return Optional.ofNullable(jdbcTemplate.queryForObject
                (QUERY_FOR_FIND_BY_PHONE, new Object[]{phone}, customerRowMapper));
    }

    @CachePut
    @Transactional
    @Override
    public void createCustomer(Customer customer) {
        jdbcTemplate.update(QUERY_FOR_CREATE,
                customer.getFirstName(), customer.getLastName(),
                customer.getPhone(),customer.getPassword(),customer.getUserName());
    }

    @CacheEvict(beforeInvocation = true)
    @Transactional
    @Override
    public boolean deleteCustomer(Long id){
        return jdbcTemplate.update(QUERY_FOR_DELETE, id)>0;
    }

    @CachePut
    @Transactional
    @Override
    public Optional<Customer> updateCustomer(Long id, Customer customer){
        jdbcTemplate.update(QUERY_FOR_UPDATE, customer.getFirstName(),customer.getLastName(),customer.getPhone(),
                customer.getPassword(),customer.getUsername(),id);
        return Optional.of(findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Autowired
    public void setCustomerRowMapper(CustomerRowMapper customerRowMapper) {
        this.customerRowMapper = customerRowMapper;
    }
}