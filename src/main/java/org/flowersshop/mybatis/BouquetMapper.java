package org.flowersshop.mybatis;

import org.apache.ibatis.annotations.*;
import org.flowersshop.entities.Bouquet;
import org.flowersshop.entities.Flower;

import java.util.List;
import java.util.Set;

@Mapper
public interface BouquetMapper {
    String FIND_BY_ID = "SELECT * FROM bouquets WHERE id = #{id}";
    String FIND_ALL = "SELECT * FROM bouquets";
    String DELETE_BY_ID = "DELETE FROM bouquets WHERE id = #{id}";
    String UPDATE = "Update bouquets set name=#{name}, price=#{price} where id=#{id}";
    String INSERT = "INSERT INTO bouquets(id, name, price) VALUES (#{id}, #{name}, #{price})";
    String FIND_FLOWERS = "Select f.id, f.name from flowers f join flowers_in_bouquet fb ON f.id = fb.flowers_id where bouquet_id = #{bouquetId}";

    //many-to-many
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "flowers",column = "id", javaType = Set.class, many=@Many(select = "findFlowers"))
    })
    @Select(FIND_BY_ID)
    Bouquet findById(Long id);

    @Select(FIND_FLOWERS)
    Set<Flower> findFlowers(Long bouquetId);

    //many-to-many
    @Results({
            @Result( property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "flowers",column = "id", javaType = Set.class, many=@Many(select = "findFlowers"))
    })
    @Select(FIND_ALL)
    List<Bouquet> findAll();

    @Delete(DELETE_BY_ID)
    void deleteById(@Param("id") Long id);

    @Update(UPDATE)
    int update(Bouquet bouquet);

    @Insert(INSERT)
    int insert(Bouquet bouquet);
}
