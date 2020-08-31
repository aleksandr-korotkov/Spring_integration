package org.flowersshop.mybatis;

import org.apache.ibatis.annotations.*;
import org.flowersshop.entities.Bouquet;
import org.flowersshop.entities.Customer;
import org.flowersshop.entities.Sale;
import org.flowersshop.entities.Shop;


import java.util.List;

@Mapper
public interface SalesMapper {
    String FIND_BY_ID = "SELECT * FROM sales WHERE id = #{id}";
    String FIND_ALL = "SELECT * FROM sales";
    String DELETE_BY_ID = "DELETE FROM sales WHERE id = #{id}";
    String UPDATE = "Update sales set name=#{name}, adress=#{address.id} where id=#{id}";
    String INSERT = "INSERT INTO sales(id, name, adress) VALUES (#{id}, #{name}, #{address.id})";
    String FIND_BOUQUETS = "Select b.id, b.name, b.price from bouquets b join sale_detail sd ON b.id = sd.bouquet_id where bouquet_id = #{saleId}";
    String FIND_CUSTOMER_BY_ID = "select * from customers where id = #{custId}";
    String FIND_SHOP_BY_ID = "select * from shop where id = #{shopId}";

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "date", column = "date"),
            @Result(property = "totalSum", column = "sum_total"),
            @Result(property = "bouquets",column = "id", javaType = List.class, many=@Many(select = "findBouquets")),
            @Result(property = "customer", javaType = Customer.class,
                    column = "customer_id", one=@One(select = "findCustomerById")),
            @Result(property = "shop", javaType = Shop.class, column = "shop_id", one=@One(select = "findShopById"))
    })
    @Select(FIND_BY_ID)
    Sale findById(Long id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "date", column = "date"),
            @Result(property = "totalSum", column = "sum_total"),
            @Result(property = "bouquets",column = "id", javaType = List.class, many=@Many(select = "findBouquets")),
            @Result(property = "customer", javaType = Customer.class,
                    column = "customer_id", one=@One(select = "findCustomerById")),
            @Result(property = "shop", javaType = Shop.class, column = "shop_id", one=@One(select = "findShopById"))
    })
    @Select(FIND_ALL)
    List<Sale> findAll();

    @Select(FIND_BOUQUETS)
    List<Bouquet> findBouquets(Long saleId);

    @Select(FIND_CUSTOMER_BY_ID)
    Customer findCustomerById(Long custId);

    @Select(FIND_SHOP_BY_ID)
    Shop findShopById(Long shopId);

    @Delete(DELETE_BY_ID)
    void deleteById(@Param("id") Long id);

    @Update(UPDATE)
    int update(Sale sale);

    @Insert(INSERT)
    int insert(Sale sale);
}
