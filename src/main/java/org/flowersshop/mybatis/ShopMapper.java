package org.flowersshop.mybatis;

import org.apache.ibatis.annotations.*;
import org.flowersshop.entities.Address;
import org.flowersshop.entities.Shop;

import java.util.List;

@Mapper
public interface ShopMapper {
    String FIND_BY_ID = "SELECT * FROM SHOP WHERE id = #{id}";
    String FIND_ALL = "SELECT * FROM SHOP";
    String DELETE_BY_ID = "DELETE FROM SHOP WHERE id = #{id}";
    String UPDATE = "Update shop set name=#{name}, adress=#{address.id} where id=#{id}";
    String INSERT = "INSERT INTO SHOP(id, name, adress) VALUES (#{id}, #{name}, #{address.id})";
    String FIND_ADDRESS_BY_ID = "SELECT * FROM SHOP WHERE id = #{id}";

    //one-to-one
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "address", javaType = Address.class,
                    column = "adress", one=@One(select = "findAddressById"))
    })
    @Select(FIND_BY_ID)
    Shop findById(Long id);

    //one-to-one
    @Results({
            @Result( property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property="address", javaType= Address.class, column="adress", many=@Many(select="findAddressById"))
    })
    @Select(FIND_ALL)
    List<Shop> findAll();

    @Select(FIND_ADDRESS_BY_ID)
    Address findAddressById(@Param("id") Long id);

    @Delete(DELETE_BY_ID)
    void deleteById(@Param("id") Long id);

    @Update(UPDATE)
    int update(Shop shop);

    @Insert(INSERT)
    int insert(Shop shop);
}