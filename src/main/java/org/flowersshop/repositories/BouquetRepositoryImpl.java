package org.flowersshop.repositories;


import org.flowersshop.entities.Bouquet;
import org.flowersshop.repositories.mappers.BouquetRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@CacheConfig(cacheNames = "bouquets")
public class BouquetRepositoryImpl implements BouquetRepository {

    private final String QUERY_FIND_ALL = "select * from bouquets";
    private final String QUERY_CREATE = "insert into bouquets(name, price) values (?,?)";
    private final String QUERY_UPDATE = "update bouquets set name = ?, price = ? where id = ?";
    private final String QUERY_DELETE = "delete from bouquets where id = ?";
    private final String QUERY_FIND_BY_NAME = "select * from bouquets where name = ?";
    private final String QUERY_FIND_BY_ID = "select * from bouquets where id = ?";
    private final String QUERY_FIND_BY_PRICE = "select * from bouquets where price = ?";
    private final String QUERY_FIND_BY_MIN_PRICE = "select * from bouquets where price > ?";
    private final String QUERY_FIND_BY_NAME_AND_PRICE = "select * from bouquets where name = ? and price = ?";

    private JdbcTemplate jdbcTemplate;
    private BouquetRowMapper bouquetRowMapper;

    @Autowired
    public BouquetRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Bouquet> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(QUERY_FIND_BY_ID,bouquetRowMapper));
    }

    @Override
    @Cacheable
    public Optional<List<Bouquet>> findByName(String name){
        return Optional.of(jdbcTemplate.query(QUERY_FIND_BY_NAME,
                new Object[]{name},bouquetRowMapper));
    }

    @Override
    @Cacheable
    public Optional<List<Bouquet>> findByPrice(BigDecimal price){
        return Optional.of(jdbcTemplate.query(QUERY_FIND_BY_PRICE,
                new Object[]{price},bouquetRowMapper));
    }

    @Override
    @Cacheable
    public Optional<List<Bouquet>> findByNameAndPrice(String name, BigDecimal price){
        return Optional.of(jdbcTemplate.query(QUERY_FIND_BY_NAME_AND_PRICE,
                new Object[]{name, price},bouquetRowMapper));
    }

    @Override
    @Cacheable
    public Optional<List<Bouquet>> findByMinPrice(BigDecimal minPrice){
        return Optional.of(jdbcTemplate.query(QUERY_FIND_BY_MIN_PRICE,
                new Object[]{minPrice},bouquetRowMapper));
    }

    @Override
    @Cacheable
    public Optional<List<Bouquet>> findAll(){
        return Optional.of(jdbcTemplate.query(QUERY_FIND_ALL, bouquetRowMapper));
    }

    @Override
    @CachePut
    public Long createBouquet(String name, BigDecimal price){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setBigDecimal(2, price);
            return ps;
            }, keyHolder);
        Integer res = (Integer) keyHolder.getKeys().get("id");
        return Long.valueOf(res);
    }

    @Override
    @CachePut
    public boolean updateBouquet(Long id, String name, BigDecimal price){
        return jdbcTemplate.update(QUERY_UPDATE, name, price, id)>0;
    }

    @Override
    @CacheEvict(beforeInvocation = true)
    public boolean deleteBouquet(Long id) {
        return jdbcTemplate.update(QUERY_DELETE, id)>0;
    }

    @Autowired
    public void setBouquetRowMapper(BouquetRowMapper bouquetRowMapper) {
        this.bouquetRowMapper = bouquetRowMapper;
    }
}
