package org.flowersshop.mybatis;

import org.apache.ibatis.annotations.*;
import org.flowersshop.entities.Address;

import java.util.List;

@Mapper
public interface AddressMapper {
    String FIND_BY_ID = "SELECT * FROM ADRESSES WHERE ID = #{id}";
    String FIND_ALL = "SELECT * FROM ADRESSES";
    String DELETE_BY_ID = "DELETE FROM ADRESSES WHERE ID = #{id}";
    String UPDATE = "UPDATE ADRESSES SET city=#{city}, street=#{street}, house=#{house}, office=#{office} where id=#{id}";
    String INSERT = "INSERT INTO ADRESSES(id, city, street, house, office) VALUES (#{id}, #{city}, #{street}, #{house}, #{office})";

    @Select(FIND_BY_ID)
    Address findById(@Param("id") Long id);

    @Select(FIND_ALL)
    List<Address> findAll();

    @Delete(DELETE_BY_ID)
    void deleteById(@Param("id") Long id);

    @Update(UPDATE)
    int update(Address address);

    @Insert(INSERT)
    int insert(Address address);
}
