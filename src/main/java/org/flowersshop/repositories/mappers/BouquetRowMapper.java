package org.flowersshop.repositories.mappers;

import org.flowersshop.bindings.Bouquet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BouquetRowMapper implements RowMapper<Bouquet> {
    @Override
    public Bouquet mapRow(ResultSet resultSet, int i) throws SQLException {
        Bouquet bouquet = new Bouquet();
        bouquet.setId(resultSet.getLong("id"));
        bouquet.setName(resultSet.getString("name"));
        bouquet.setPrice(resultSet.getBigDecimal("price"));
        return bouquet;
    }
}