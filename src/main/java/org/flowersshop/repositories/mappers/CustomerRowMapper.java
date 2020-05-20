package org.flowersshop.repositories.mappers;

import org.flowersshop.entities.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Customer(
                resultSet.getLong("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("phone"),
                resultSet.getString("username")
                );
    }
}